package com.skrpld.matule.ui.screens.projects

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.skrpld.matule.data.models.Project
import com.skrpld.uikit.R
import com.skrpld.uikit.components.TabBar
import com.skrpld.uikit.components.buttons.ButtonStyle
import com.skrpld.uikit.components.buttons.CommonButton
import com.skrpld.uikit.theme.Black
import com.skrpld.uikit.theme.Caption
import com.skrpld.uikit.theme.Description
import com.skrpld.uikit.theme.InputBackground
import com.skrpld.uikit.theme.White
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProjectsScreen(
    onNavigateToTab: (Int) -> Unit,
    onAddProject: () -> Unit,
    viewModel: ProjectsViewModel = koinViewModel(),
) {
    val projects by viewModel.projects.collectAsState()

    Scaffold(
        bottomBar = {
            TabBar(
                selectedIndex = 2,
                onItemSelected = { index -> onNavigateToTab(index) }
            )
        },
        containerColor = InputBackground
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
                ProjectsHeader(
                    onAddClick = onAddProject
                )
            }

            items(
                items = projects,
                key = { it.id }
            ) { project ->
                ProjectItemCard(
                    project = project,
                    onOpenClick = { viewModel.onOpenProject(project.id) }
                )
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

@Composable
fun ProjectsHeader(
    onAddClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = "Проекты",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = Black,
            modifier = Modifier.align(Alignment.Center)
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_plus),
            contentDescription = "Add Project",
            tint = Caption,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .size(28.dp)
                .clickable { onAddClick() }
        )
    }
}

@Composable
fun ProjectItemCard(
    project: Project,
    onOpenClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = project.title,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium),
                color = Black,
                maxLines = 1
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = project.dateDisplay,
                    style = MaterialTheme.typography.labelMedium,
                    color = Description
                )

                CommonButton(
                    text = "Открыть",
                    isSmall = true,
                    style = ButtonStyle.Active,
                    onClick = onOpenClick,
                    modifier = Modifier.width(100.dp)
                )
            }
        }
    }
}