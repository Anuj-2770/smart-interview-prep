# 🎯 Smart Interview Prep Platform

An AI-powered interview preparation platform built for Indian engineering students, helping them practice technical and HR interview questions with real-time AI feedback.

## 🚀 Features

- **🔐 Secure Authentication** — JWT-based login and registration with Spring Security
- **📚 Topic-Based Practice** — Choose from DSA, Java, Spring Boot, and HR question categories
- **🤖 AI Question Generation** — Dynamic interview questions generated using Google's Gemini AI
- **🎤 Voice Input** — Answer questions hands-free using the Web Speech API
- **✅ AI Answer Evaluation** — Get instant feedback, scoring, and ideal answers powered by AI
- **🔍 Google Search Fallback** — Quickly search any question online when needed
- **📊 Progress Tracking** — Track topic-wise practice history and questions solved
- **🗑️ Manage Progress** — Delete or update saved progress entries

## 🛠️ Tech Stack

**Backend**
- Java 17
- Spring Boot 3.3.5
- Spring Security + JWT
- Spring Data JPA / Hibernate
- MySQL

**Frontend**
- HTML, CSS, JavaScript
- Web Speech API (voice recognition)

**AI Integration**
- Google Gemini API (`gemini-2.0-flash`)

**Tools**
- Eclipse, Postman, Git/GitHub, SQLyog

## 📐 Architecture

```
User → Login/Register (JWT) → Dashboard → Select Topic
                                              ↓
                                    Gemini AI generates questions
                                              ↓
                          User answers (typed or voice) → AI evaluates
                                              ↓
                                    Progress saved to MySQL
```

## 🔑 Key API Endpoints

| Method | Endpoint | Description |
|--------|----------|--------------|
| POST | `/api/auth/register` | Register a new user |
| POST | `/api/auth/login` | Login and receive JWT token |
| GET | `/api/topics/all` | Fetch all available topics |
| GET | `/api/questions/generate` | Generate AI questions for a topic |
| POST | `/api/answer/evaluate` | Evaluate a user's answer using AI |
| POST | `/api/progress/save` | Save practice progress |
| GET | `/api/progress/my` | Get logged-in user's progress |
| DELETE | `/api/progress/delete/{id}` | Delete a progress entry |
| GET | `/api/user/profile` | Get logged-in user's profile |

## ⚙️ Getting Started

### Prerequisites
- Java 17+
- MySQL 8.0+
- A Gemini API key from [Google AI Studio](https://aistudio.google.com)

### Setup

1. Clone the repository
   ```bash
   git clone https://github.com/Anuj-2770/smart-interview-prep.git
   ```

2. Create a MySQL database
   ```sql
   CREATE DATABASE interview_prep_db;
   ```

3. Configure `src/main/resources/application.properties`
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/interview_prep_db
   spring.datasource.username=root
   spring.datasource.password=YOUR_PASSWORD
   gemini.api.key=YOUR_GEMINI_API_KEY
   server.port=8282
   ```

4. Run the application and open
   ```
   http://localhost:8282/login.html
   ```

## 🎯 What I Learned

Building this project helped me get hands-on experience with:
- Implementing secure authentication using JWT and Spring Security
- Integrating a third-party AI API into a Spring Boot backend
- Handling API rate limits with fallback mechanisms for reliability
- Building a responsive frontend without a framework
- Working with the Web Speech API for voice-based interaction
- Managing relational data with JPA/Hibernate

## 📌 Future Improvements

- Migrate frontend to React for better state management
- Add multi-language support for question generation
- Deploy on AWS EC2 with CI/CD pipeline
- Add leaderboard and gamification features

## 👤 Author

**Anuj Yadav**
- GitHub: [@Anuj-2770](https://github.com/Anuj-2770)
- LinkedIn: [Anuj Yadav](https://www.linkedin.com/in/anujyadav9005/)
- Email: anujyadav221307vns@gmail.com
