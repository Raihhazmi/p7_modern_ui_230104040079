# HabitConnect 🚀 --- Modern Habit Tracking App (Jetpack Compose + Material 3)

**HabitConnect** adalah aplikasi pelacak kebiasaan (**Habit Tracker**)
modern berbasis Android yang dikembangkan menggunakan **Kotlin** dan
**Jetpack Compose**. Aplikasi ini dibangun untuk memenuhi tugas
**Praktikum Mobile Programming #7 -- Menerapkan Desain UI Modern**,
sekaligus sebagai contoh implementasi lengkap dari **Material Design 3
(Material You)** dengan tema yang adaptif, responsif, dan konsisten.

------------------------------------------------------------------------

## 🔥 Highlight Fitur & Teknologi

HabitConnect dirancang agar terasa seperti aplikasi modern di Play
Store, dengan fokus pada **kebersihan UI**, **pengalaman pengguna**, dan
**kemudahan pengembangan**.

### 🎨 1. Material Design 3 (You)

-   Dynamic Color (otomatis mengikuti wallpaper Android 12+)
-   Custom Color Scheme untuk device lama
-   Typography M3 + Rounded Shapes modern
-   Consistent spacing & elevation guideline

### 🌙 2. Light & Dark Theme

-   Switching manual via Settings
-   Auto-follow system theme
-   State persisted

### 🤏 3. Interactive UI

-   Swipe-to-delete gesture
-   Modern buttons
-   Animated transitions

### 📸 4. Profile Photo Picker

-   Mengambil gambar dari galeri
-   Preview langsung di Profile Screen

### 🏆 5. Gamification & Statistik

-   Hitungan streak
-   Grafik progres mingguan
-   Achievement badges

### 📅 6. Smart Dashboard

-   Calendar strip horizontal
-   Kategori kebiasaan
-   Progress card harian

### 🧩 7. Reusable UI Components

Tersedia di folder `components/`: - AppButton - AppCard - AppTextField -
AppTopBar

------------------------------------------------------------------------

## 🛠️ Stack Teknologi

| Teknologi                     | Deskripsi                                   |
|------------------------------|----------------------------------------------|
| **Kotlin**                   | Bahasa utama Android modern                  |
| **Jetpack Compose**          | UI declarative modern                        |
| **Material Design 3**        | Sistem desain utama aplikasi                 |
| **Navigation Compose**       | Navigasi antar-screen                        |
| **Coil**                     | Image loader ringan untuk gambar profil      |
| **Coroutines & StateFlow**   | Manajemen alur data reaktif                  |
| **Android Studio Ladybug/Koala** | IDE pengembangan                          |
------------------------------------------------------------------------

## 📂 Struktur Proyek
```
  id.antasari.p7_modern_ui_230104040079
│
├── ui/
│   ├── components/           # Reusable composables
│   │   ├── AppButton.kt
│   │   ├── AppCard.kt
│   │   ├── AppTextField.kt
│   │   └── AppTopBar.kt
│   │
│   ├── screen/               # Semua halaman
│   │   ├── LoginScreen.kt
│   │   ├── HomeScreen.kt
│   │   ├── ProfileScreen.kt
│   │   └── SettingsScreen.kt
│   │
│   └── theme/                # Material 3 Theme Setup
│       ├── Color.kt
│       ├── Shape.kt
│       ├── Theme.kt
│       └── Type.kt
│
│
└── MainActivity.kt           # Entry point + Navigation graph
```
------------------------------------------------------------------------
## 📸 Screenshots (UI Preview)

| Login Screen | Home Screen | Profile Screen |
|-------------|-------------| -------------|
| <img src="https://github.com/Raihhazmi/p7_modern_ui_230104040079/blob/master/Screenshots/Login_Screen.png" width="250" /> | <img src="https://github.com/Raihhazmi/p7_modern_ui_230104040079/blob/master/Screenshots/Home_Screen.png" width="250" /> | <img src="https://github.com/Raihhazmi/p7_modern_ui_230104040079/blob/master/Screenshots/Profile_Screen.png" width="250" /> |
| Settings | Home Screen Dark Mode  | Profile Screen Dark Mode  |
| <img src="https://github.com/Raihhazmi/p7_modern_ui_230104040079/blob/master/Screenshots/Setting_Screen.png" width="250" /> | <img src="https://github.com/Raihhazmi/p7_modern_ui_230104040079/blob/master/Screenshots/Home_Screen_DarkMode.png" width="250" /> | <img src="https://github.com/Raihhazmi/p7_modern_ui_230104040079/blob/master/Screenshots/Proflie_Screen_DarkMode.png" width="250" /> | 
|    |  Setting Screen Dark Mode  |    |
|  | <img src="https://github.com/Raihhazmi/p7_modern_ui_230104040079/blob/master/Screenshots/Setting_Screen_DarkMode.png" width="250" /> |  | 


-----

## 🚀 Cara Menjalankan Proyek

### 1. Clone Repository
```bash
git clone https://github.com/username-anda/HabitConnect.git
```
2. Buka di Android Studio
Pastikan menggunakan Android Studio Ladybug / Koala atau versi yang lebih baru.

3. Tunggu Gradle Sync
Biarkan Android Studio melakukan sinkronisasi dependensi secara otomatis.

4. Jalankan Aplikasi
Bisa dijalankan pada Emulator atau Perangkat Fisik

Minimum SDK: 24 (Android 7.0)

---

### 📌 Fitur Tambahan yang Bisa Kamu Kembangkan

Jika ingin meningkatkan proyek ini menjadi aplikasi portofolio profesional, kamu bisa menambahkan fitur berikut:

-   🔐 Firebase Authentication — Login & register nyata
-   🗂️ Local Database (Room) — Penyimpanan habit secara offline
-   ⏰ Habit Reminders (AlarmManager) — Pengingat otomatis
-   📄 Export Progress ke PDF — Rekap progres habit
-   🟣 Dynamic Island–style Progress Indicator — Notifikasi progres yang interaktif

------------------------------------------------------------------------

## 👤 Author

**Muhammad Raihan Azmi**\
NIM: 230104040079\
UIN Antasari Banjarmasin\
Mata Kuliah: Mobile Programming
