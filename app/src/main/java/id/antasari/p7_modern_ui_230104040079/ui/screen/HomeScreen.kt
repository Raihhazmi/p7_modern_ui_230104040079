package id.antasari.p7_modern_ui_230104040079.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
// Import komponen custom
import id.antasari.p7_modern_ui_230104040079.ui.components.AppCard
import id.antasari.p7_modern_ui_230104040079.ui.components.AppTextField
import id.antasari.p7_modern_ui_230104040079.ui.components.AppTopBar

// Data Class
data class Habit(
    val id: Int,
    val title: String,
    val target: String,
    val progress: Float,
    val category: String, // New: Category support
    val icon: ImageVector,
    var isCompleted: Boolean = false
)

// Dummy Date Data
data class CalendarDay(val day: String, val date: String, val isToday: Boolean = false)

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    // --- STATE MANAGEMENT ---

    // 1. Data Habit List
    val habits = remember {
        mutableStateListOf(
            Habit(1, "Morning Jogging", "5.0 km", 0.7f, "Health", Icons.Default.FitnessCenter),
            Habit(2, "Read Book", "30 mins", 0.4f, "Study", Icons.Default.MenuBook),
            Habit(3, "Drink Water", "2 Liters", 0.9f, "Health", Icons.Default.WaterDrop)
        )
    }

    // 2. Filter Category State
    val categories = listOf("All", "Health", "Study", "Mindset")
    var selectedCategory by remember { mutableStateOf("All") }

    // 3. Filtered List Logic
    val filteredHabits = if (selectedCategory == "All") {
        habits
    } else {
        habits.filter { it.category == selectedCategory }
    }

    // 4. Dialog State
    var showDialog by remember { mutableStateOf(false) }
    var newHabitName by remember { mutableStateOf("") }
    var newHabitTarget by remember { mutableStateOf("") }

    // 5. Calendar Selection State
    var selectedDateIndex by remember { mutableIntStateOf(2) } // Default select 'Wed 20'

    Scaffold(
        topBar = {
            // Custom Header yang lebih modern (Ganti AppTopBar standar)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(top = 16.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)
            ) {
                Text(
                    text = "Hello, Raihan! \uD83D\uDC4B",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = "Let's crush your goals today.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showDialog = true },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White,
                shape = RoundedCornerShape(16.dp) // Soft rounded
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Habit")
            }
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(bottom = 80.dp), // Extra padding for FAB
        ) {
            // --- SECTION 1: HORIZONTAL CALENDAR ---
            item {
                val days = listOf(
                    CalendarDay("Mon", "18"),
                    CalendarDay("Tue", "19"),
                    CalendarDay("Wed", "20", isToday = true),
                    CalendarDay("Thu", "21"),
                    CalendarDay("Fri", "22"),
                    CalendarDay("Sat", "23"),
                )

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    modifier = Modifier.padding(vertical = 16.dp)
                ) {
                    itemsIndexed(days) { index, day ->
                        DateItem(
                            day = day,
                            isSelected = index == selectedDateIndex,
                            onClick = { selectedDateIndex = index }
                        )
                    }
                }
            }

            // --- SECTION 2: PREMIUM BANNER CARD ---
            item {
                Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                    // Gradient Card
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(20.dp))
                            .background(
                                Brush.horizontalGradient(
                                    colors = listOf(
                                        MaterialTheme.colorScheme.primary,
                                        MaterialTheme.colorScheme.tertiary
                                    )
                                )
                            )
                            .padding(20.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "Your Daily Streak",
                                    style = MaterialTheme.typography.labelLarge,
                                    color = Color.White.copy(alpha = 0.8f)
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "5 Days \uD83D\uDD25",
                                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                                    color = Color.White
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "You're on fire! Keep it up.",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color.White.copy(alpha = 0.9f)
                                )
                            }
                            Icon(
                                imageVector = Icons.Default.EmojiEvents,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(64.dp)
                            )
                        }
                    }
                }
            }

            // --- SECTION 3: CATEGORY CHIPS ---
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    modifier = Modifier.padding(vertical = 16.dp)
                ) {
                    items(categories) { category ->
                        FilterChip(
                            selected = selectedCategory == category,
                            onClick = { selectedCategory = category },
                            label = { Text(category) },
                            leadingIcon = if (selectedCategory == category) {
                                { Icon(Icons.Default.CheckCircle, null, modifier = Modifier.size(16.dp)) }
                            } else null,
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                                selectedLabelColor = MaterialTheme.colorScheme.primary
                            ),
                            border = FilterChipDefaults.filterChipBorder(
                                borderColor = Color.LightGray.copy(alpha = 0.4f),
                                enabled = true,
                                selected = selectedCategory == category
                            )
                        )
                    }
                }
            }

            // --- SECTION 4: HABIT LIST (With Empty State) ---
            if (filteredHabits.isEmpty()) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.Spa,
                            contentDescription = null,
                            tint = Color.LightGray,
                            modifier = Modifier.size(80.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text("No habits found", color = Color.Gray)
                    }
                }
            } else {
                items(
                    items = filteredHabits,
                    key = { it.id }
                ) { habit ->
                    SwipeToDeleteContainer(
                        item = habit,
                        onDelete = { habits.remove(habit) }
                    ) {
                        HabitItem(habit = habit)
                    }
                }
            }
        }

        // --- DIALOG (Sama seperti sebelumnya) ---
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("New Habit", fontWeight = FontWeight.Bold) },
                text = {
                    Column {
                        AppTextField(value = newHabitName, onValueChange = { newHabitName = it }, label = "Habit Name", placeholder = "e.g. Gym")
                        Spacer(modifier = Modifier.height(8.dp))
                        AppTextField(value = newHabitTarget, onValueChange = { newHabitTarget = it }, label = "Target", placeholder = "e.g. 1 Hour")
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            if (newHabitName.isNotEmpty() && newHabitTarget.isNotEmpty()) {
                                habits.add(Habit((habits.maxOfOrNull { it.id } ?: 0) + 1, newHabitName, newHabitTarget, 0.0f, "Health", Icons.Default.Bolt))
                                newHabitName = ""
                                newHabitTarget = ""
                                showDialog = false
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                    ) { Text("Create") }
                },
                dismissButton = { TextButton(onClick = { showDialog = false }) { Text("Cancel", color = Color.Gray) } },
                containerColor = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(24.dp)
            )
        }
    }
}

// --- KOMPONEN BARU: DATE ITEM (TANGGAL) ---
@Composable
fun DateItem(day: CalendarDay, isSelected: Boolean, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .width(60.dp)
            .height(80.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(
                if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface
            )
            .border(
                width = 1.dp,
                color = if (isSelected) Color.Transparent else Color.LightGray.copy(alpha = 0.3f),
                shape = RoundedCornerShape(16.dp)
            )
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = day.day,
            style = MaterialTheme.typography.labelMedium,
            color = if (isSelected) Color.White.copy(alpha = 0.8f) else Color.Gray
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = day.date,
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface
        )
        if (day.isToday) {
            Spacer(modifier = Modifier.height(4.dp))
            Box(
                modifier = Modifier
                    .size(4.dp)
                    .clip(CircleShape)
                    .background(if (isSelected) Color.White else MaterialTheme.colorScheme.primary)
            )
        }
    }
}

// --- WRAPPER SWIPE (Sama seperti sebelumnya) ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> SwipeToDeleteContainer(
    item: T,
    onDelete: (T) -> Unit,
    animationDuration: Int = 500,
    content: @Composable (T) -> Unit
) {
    var isRemoved by remember { mutableStateOf(false) }
    val state = rememberSwipeToDismissBoxState(
        confirmValueChange = { value ->
            if (value == SwipeToDismissBoxValue.EndToStart) {
                isRemoved = true
                true
            } else { false }
        }
    )

    LaunchedEffect(isRemoved) {
        if (isRemoved) {
            delay(animationDuration.toLong())
            onDelete(item)
        }
    }

    AnimatedVisibility(
        visible = !isRemoved,
        exit = shrinkVertically(animationSpec = tween(durationMillis = animationDuration), shrinkTowards = Alignment.Top) + fadeOut()
    ) {
        SwipeToDismissBox(
            state = state,
            backgroundContent = {
                val color = if (state.dismissDirection == SwipeToDismissBoxValue.EndToStart) Color.Red.copy(alpha = 0.8f) else Color.Transparent
                Box(modifier = Modifier.fillMaxSize().padding(16.dp).background(color, RoundedCornerShape(16.dp)).padding(end = 24.dp), contentAlignment = Alignment.CenterEnd) {
                    Icon(Icons.Default.Delete, null, tint = Color.White)
                }
            },
            content = { content(item) },
            enableDismissFromStartToEnd = false
        )
    }
}

// --- ITEM HABIT YANG DIPERBAGUS ---
@Composable
fun HabitItem(habit: Habit) {
    Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        AppCard(modifier = Modifier.fillMaxWidth()) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(4.dp)) {
                // Icon Box dengan Warna Dinamis berdasarkan Kategori
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(
                            if (habit.category == "Health") Color(0xFFE3F2FD) else Color(0xFFFFF3E0) // Biru untuk Health, Oranye untuk Study
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = habit.icon,
                        contentDescription = null,
                        tint = if (habit.category == "Health") Color(0xFF1565C0) else Color(0xFFE65100)
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(text = habit.title, style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold))
                    Text(text = "${habit.category} • ${habit.target}", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                    Spacer(modifier = Modifier.height(10.dp))

                    // Progress Bar yang lebih tebal dan rounded
                    LinearProgressIndicator(
                        progress = { habit.progress },
                        modifier = Modifier.fillMaxWidth().height(8.dp).clip(CircleShape),
                        color = MaterialTheme.colorScheme.primary,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                // Checkbox Besar
                Checkbox(
                    checked = habit.isCompleted,
                    onCheckedChange = { habit.isCompleted = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = MaterialTheme.colorScheme.primary,
                        checkmarkColor = Color.White
                    ),
                    modifier = Modifier.size(48.dp) // Memperbesar area sentuh
                )
            }
        }
    }
}