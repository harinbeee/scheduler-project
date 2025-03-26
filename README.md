# 💡 일정관리 앱 project
간단한 일정 관리 앱 API입니다
일정을 등록하고 수정하거나 삭제할 수 있고, 작성자명이나 날짜를 통해 조회할 수 있습니다

## ⚙️ 개발 환경
- Java : 17 
- JDK : 17.0.1
- Spring Boot : 3.4.4
- IDE : IntelliJ
- Build Tool : Gradle 
- API Test : Postman 

## 📑 [API 명세서](https://documenter.getpostman.com/view/43200298/2sAYkKJy7F) 
( ↳ 실행 예시까지 자세히 보기 )
| 기능 | Method | URL | 요청(Request) | 응답(Response) | 상태 코드 |
|------|--------|-----|----------------|-----------------|-------------|
| 📝 일정 등록 | POST | `/schedules` | **Body (JSON)**<br>`todo`, `user`, `password` | 등록 정보 | `201 CREATED` |
| 📋 전체 일정 조회 | GET | `/schedules` | **Query Param**<br>`date`, `user` (선택) | 다건 응답 정보 (List) | `200 OK` |
| 🔍 선택 일정 조회 | GET | `/schedules/{id}` | **Path Variable**<br>`id` | 단건 응답 정보 | `200 OK` |
| 🛠️ 선택 일정 수정 | PATCH | `/schedules/{id}` | **Path Variable**<br>`id`<br>**Body (JSON)**<br>`todo`, `user`, `password` | 수정 정보 | `200 OK` |
| 🗑️ 선택 일정 삭제 | DELETE | `/schedules/{id}` | **Path Variable**<br>`id`<br>**Body (JSON)**<br>`password` | - | `200 OK` |

## 🔖 [ERD](https://github.com/user-attachments/assets/7bb08939-70d7-4be3-961e-c1bbe11b05a2)

<br>
