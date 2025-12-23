package com.skrpld.matule.ui.screens.projects

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.skrpld.matule.data.models.Project
import com.skrpld.matule.ui.components.AppBottomBar
import com.skrpld.matule.ui.navigation.AppNavigation
import com.skrpld.matule.ui.theme.Accent
import com.skrpld.matule.ui.theme.Black
import com.skrpld.matule.ui.theme.Caption
import com.skrpld.matule.ui.theme.Description
import com.skrpld.matule.ui.theme.InputBackground
import com.skrpld.matule.ui.theme.Typography
import com.skrpld.matule.ui.theme.White

@Composable
fun ProjectsScreen(
    navController: NavController,
    viewModel: ProjectsViewModel = hiltViewModel(),
) {
    val navigation = remember(navController) { AppNavigation(navController as androidx.navigation.NavHostController) }
    val projects by viewModel.projects.collectAsState()

    Scaffold(
        bottomBar = { AppBottomBar(navController) }, // Активная вкладка должна управляться внутри BottomBar
        containerColor = InputBackground
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Заголовок и кнопка добавления
            item {
                Spacer(modifier = Modifier.height(16.dp))
                ProjectsHeader(
                    onAddClick = { viewModel.onAddProjectClick(navigation) }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            // Список проектов
            items(projects) { project ->
                ProjectItemCard(
                    project = project,
                    onOpenClick = { viewModel.onOpenProject(project.id) }
                )
            }

            // Отступ снизу для красоты
            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

// --- Компоненты ---

@Composable
fun ProjectsHeader(
    onAddClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Чтобы текст был ровно по центру, используем Box или weight,
        // но так как справа есть иконка, используем трюк с весом или Box.
        // Здесь простой вариант:

        // Пустой Box для баланса (если нужна строгая центровка заголовка относительно экрана)
        // Но на скриншоте заголовок по центру, а плюс справа.
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Проекты",
                style = Typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = Black,
                modifier = Modifier.align(Alignment.Center)
            )

            Icon(
                imageVector = Icons.Default.Add, // Или painterResource(R.drawable.ic_plus)
                contentDescription = "Add Project",
                tint = Caption, // Серый цвет как на макете
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .clickable { onAddClick() }
                    .size(28.dp)
            )
        }
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
            .height(110.dp), // Фиксированная высота или wrapContent
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
            // Название проекта
            Text(
                text = project.title,
                style = Typography.bodyLarge.copy(fontWeight = FontWeight.Medium), // Чуть жирнее обычного
                color = Black
            )

            // Нижняя строка: Дата и Кнопка
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = project.dateDisplay,
                    style = Typography.labelMedium,
                    color = Description // Светло-серый цвет
                )

                Button(
                    onClick = onOpenClick,
                    colors = ButtonDefaults.buttonColors(containerColor = Accent),
                    shape = RoundedCornerShape(10.dp), // Скругление кнопки
                    contentPadding = PaddingValues(horizontal = 20.dp, vertical = 0.dp),
                    modifier = Modifier.height(34.dp) // Компактная кнопка
                ) {
                    Text(
                        text = "Открыть",
                        style = Typography.labelLarge.copy(
                            color = White,
                            fontWeight = FontWeight.Medium,
                            fontSize = 13.sp
                        )
                    )
                }
            }
        }
    }
}