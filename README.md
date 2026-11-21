# Smart Note App - Ứng Dụng Ghi Chú Thông Minh

> Dự án xây dựng ứng dụng ghi chú Android hiện đại, tích hợp các tính năng chuẩn mực cùng công nghệ AI và khả năng quản lý tri thức vượt trội.

---

## 📌 1. Tổng Quan Dự Án (Project Overview)

**Smart Note App** là ứng dụng ghi chú cá nhân trên nền tảng Android, giúp người dùng ghi lại ý tưởng, quản lý công việc và lưu trữ thông tin một cách nhanh chóng, khoa học và bảo mật.

Dự án không chỉ đáp ứng trọn vẹn các tính năng ghi chú thiết yếu của các ứng dụng hàng đầu hiện nay (Google Keep, Notion, Apple Notes) mà còn mở rộng các **tính năng thông minh tiên tiến** (AI Assistant, OCR, liên kết ghi chú Bi-directional Link) nhằm mang lại trải nghiệm ghi chú tối ưu nhất cho học sinh, sinh viên và người làm việc văn phòng.

---

## 🎯 2. Các Tính Năng Cốt Lõi (Core Features)

### 📝 Quản Lý Ghi Chú Cơ Bản
- **Tạo, Chỉnh sửa & Xóa**: Hỗ trợ tạo ghi chú văn bản nhanh, chỉnh sửa linh hoạt.
- **Thùng Rác (Trash / Recycling Bin)**: Khôi phục ghi chú đã xóa hoặc xóa vĩnh viễn sau khoảng thời gian tùy chỉnh.
- **Ghim & Yêu thích (Pin & Favorites)**: Đưa các ghi chú quan trọng lên đầu danh sách.
- **Màu sắc & Chủ đề thẻ ghi chú**: Phân loại ghi chú visually bằng cách đổi màu nền hoặc ảnh nền cho từng card ghi chú.

### ✍️ Định Dạng Văn Bản Phong Phú (Rich Text Editor)
- Hỗ trợ định dạng **Bold**, *Italic*, <u>Underline</u>, Strikethrough, Code Block.
- Danh sách công việc (Checklist / To-do list) với thanh tiến độ hoàn thành.
- Danh sách dạng đầu dòng (Bullet points) và số (Numbered lists).

### 🏷️ Phân Loại & Tìm Kiếm Thông Minh
- **Thẻ (Tags) & Thư mục (Folders/Categories)**: Gán nhiều tag cho 1 ghi chú để dễ dàng phân loại.
- **Tìm kiếm đa năng**: Tìm kiếm theo từ khóa thực tế trong tiêu đề, nội dung, màu sắc hoặc tag.
- **Bộ lọc & Sắp xếp**: Sắp xếp theo ngày tạo, ngày cập nhật gần nhất, tiêu đề A-Z hoặc mức độ ưu tiên.

### 🎙️ Đa Phương Tiện (Multimedia Support)
- **Đính kèm hình ảnh**: Thêm ảnh từ thư viện hoặc chụp trực tiếp từ camera.
- **Ghi âm (Voice Notes)**: Thu âm lời nói trực tiếp vào ghi chú với trình phát âm thanh tích hợp.
- **Vẽ tay / Phác thảo (Handwriting & Sketching)**: Tạo bản vẽ tự do trực tiếp trên màn hình cảm ứng.

### ⏰ Nhắc Nhở & Bảo Mật
- **Thông báo nhắc nhở (Reminders)**: Đặt lịch nhắc nhở theo thời gian cụ thể hoặc lặp lại (hàng ngày/hàng tuần).
- **Khóa ghi chú riêng tư (Biometric & PIN Lock)**: Bảo vệ ghi chú quan trọng bằng sinh trắc học (vân tay/khuôn mặt) hoặc mã PIN.
- **Chế độ Giao diện**: Tự động chuyển đổi giữa Dark Mode và Light Mode theo hệ thống.

---

## 🚀 3. Các Tính Năng Đặc Biệt & Đột Phá (Unique / Advanced Features)

### 🤖 Trợ Lý AI Thông Minh (AI Note Assistant)
- **Tóm tắt tự động (AI Summarization)**: Tóm tắt nội dung các ghi chú dài thành các ý chính ngắn gọn.
- **Quét văn bản từ hình ảnh (OCR - Image to Text)**: Trích xuất chữ từ tài liệu, bảng hiệu, sách vở chụp bằng camera và chèn trực tiếp vào ghi chú.
- **Chuyển Giọng Nói Thành Văn Bản (Speech-to-Text)**: Tự động ghi chép lời nói thành văn bản chuẩn xác với khả năng tự động xuống dòng và ngắt câu.

### 🔗 Ghi Chú Liên Kết (Bi-directional Linking & Knowledge Graph)
- **Liên kết giữa các ghi chú (`[[Note Title]]`)**: Cho phép kết nối các ghi chú có liên quan với nhau để tạo thành mạng lưới kiến thức cá nhân (Zettelkasten style).
- **Sơ đồ tư duy (Graph View)**: Trực quan hóa mối quan hệ giữa các ghi chú dưới dạng đồ thị liên kết.

### 📲 Thao Tác Nhanh & Widget
- **Widget Màn Hình Chính (HomeScreen Widgets)**: Xem nhanh ghi chú, checklist công việc và nút tạo ghi chú 1-tap ngay từ màn hình chính.
- **Cửa Sổ Nổi (Floating Bubble Note)**: Tạo ghi chú nhanh đè lên các ứng dụng khác mà không cần chuyển đổi ứng dụng.

### ☁️ Đồng Bộ & Xuất Dữ Liệu (Cloud Sync & Multi-format Export)
- **Sao lưu & Đồng bộ**: Đồng bộ dữ liệu an toàn qua Cloud (Firebase / Google Drive).
- **Xuất dữ liệu linh hoạt**: Xuất ghi chú dưới dạng tệp **PDF**, **Markdown (.md)**, **TXT** hoặc **Hình ảnh**.

---

## 🛠️ 4. Kiến Trúc & Công Nghệ Dự Kiến (Tech Stack)

| Thành phần | Công nghệ / Thư viện đề xuất |
| :--- | :--- |
| **Nền tảng** | Android Native (Kotlin) |
| **Giao diện (UI)** | Jetpack Compose + Material Design 3 |
| **Kiến trúc (Architecture)** | MVVM / Clean Architecture |
| **Cơ sở dữ liệu cục bộ** | Room Database |
| **Bất đồng bộ & Data Stream** | Kotlin Coroutines & Flow / StateFlow |
| **Dependency Injection** | Hilt / Koin |
| **Đồng bộ / Cloud Backend** | Firebase (Firestore, Auth, Storage) |
| **Nhận diện chữ (OCR)** | ML Kit Text Recognition |
| **Định dạng văn bản** | RichText Compose / Markwon |

---

## 📅 5. Lộ Trình Phát Triển (Roadmap)

- [ ] **Giai đoạn 1: Nền tảng (Core Foundation)**
  - Thiết lập kiến trúc dự án (MVVM + Jetpack Compose + Room DB).
  - Hoàn thiện giao diện danh sách & tạo/sửa ghi chú cơ bản.
  - Quản lý CRUD (Create, Read, Update, Delete) & Thùng rác.

- [ ] **Giai đoạn 2: Nâng cấp Trải nghiệm & Đa phương tiện**
  - Tích hợp Rich Text Editor (định dạng văn bản, checklist).
  - Hỗ trợ đính kèm hình ảnh, ghi âm và vẽ tay.
  - Quản lý Tags, Phân loại, Tìm kiếm & Lọc.
  - Hỗ trợ Dark Mode & tùy chỉnh màu thẻ ghi chú.

- [ ] **Giai đoạn 3: Tính năng Thông minh & Bảo mật**
  - Tích hợp OCR (nhận diện chữ từ ảnh) & Speech-to-Text.
  - Tích hợp AI Tóm tắt ghi chú.
  - Khóa sinh trắc học (vân tay/mã PIN) cho ghi chú riêng tư.
  - HomeScreen Widget & Floating Bubble.

- [ ] **Giai đoạn 4: Đồng bộ, Liên kết & Hoàn thiện**
  - Tích hợp liên kết hai chiều `[[Bi-directional Link]]` & Graph View.
  - Đồng bộ Cloud (Firebase / Google Drive) & Export (PDF, Markdown).
  - Kiểm thử, tối ưu hiệu năng và đóng gói ứng dụng.

---

## 🤝 6. Đóng Góp & Phát Triển (Contributing)
Mọi đóng góp, báo lỗi hoặc đề xuất tính năng mới đều được hoan nghênh. Vui lòng tạo Issue hoặc Pull Request trong repository này.
