import Recommendations from "./pages/Recommendations";
import Progress from "./pages/Progress";
import Nutrition from "./pages/Nutrition";
import Workouts from "./pages/Workouts";
import Calories from "./pages/Calories";
import BMI from "./pages/BMI";
import { BrowserRouter, Routes, Route, Link, useNavigate } from "react-router-dom";
import Home from "./Home";
import Login from "./pages/Login";
import Register from "./pages/Register";


function Dashboard() {
  const userName = localStorage.getItem("name") || "Athlete";

  const cards = [
  { icon: "⚖️", title: "BMI Calculator", desc: "Track your body mass index", link: "/bmi" },
  { icon: "🔥", title: "Calories Tracker", desc: "Calculate daily calorie needs", link: "/calories" },
  { icon: "🏋️", title: "Workout Plans", desc: "Explore exercises and routines", link: "/workouts" },
  { icon: "🥗", title: "Nutrition Plans", desc: "Track meals and macros", link: "/nutrition" },
  { icon: "📈", title: "Progress Tracking", desc: "Monitor your fitness journey", link: "/progress" },
  { icon: "🤖", title: "AI Recommendations", desc: "Get personalized fitness guidance", link: "/recommendations" },
];

  return (
    <div style={{ minHeight: "100vh", background: "#0b0e0c", color: "#f4f6f4", padding: "35px", fontFamily: "Arial, sans-serif" }}>
      <header style={{ display: "flex", justifyContent: "space-between", alignItems: "center" }}>
        <h2 style={{ color: "#c5ff41" }}>FITFORGE AI</h2>
        <button onClick={() => { localStorage.clear(); window.location.href = "/login"; }}
          style={{ padding: "10px 20px", background: "#c5ff41", border: "none", borderRadius: "7px", cursor: "pointer" }}>
          Logout
        </button>
      </header>

      <section style={{ textAlign: "center", margin: "45px 0 30px" }}>
        <p style={{ color: "#c5ff41", letterSpacing: "2px" }}>YOUR FITNESS JOURNEY STARTS HERE</p>
        <h1 style={{ fontSize: "36px" }}>Welcome, {userName}!</h1>
        <p style={{ color: "#a5aca6" }}>Build strength. Track progress. Become your best self.</p>
      </section>

      <div style={{ display: "grid", gridTemplateColumns: "repeat(auto-fit, minmax(230px, 1fr))", gap: "20px" }}>
        {cards.map((card) => (
          <div key={card.title} style={{ background: "#151a16", border: "1px solid #303a30", borderRadius: "14px", padding: "25px" }}>
            <div style={{ fontSize: "32px" }}>{card.icon}</div>
            <h3 style={{ color: "#c5ff41" }}>{card.title}</h3>
            <p style={{ color: "#a5aca6" }}>{card.desc}</p>
            <Link to={card.link} style={{ display: "inline-block", color: "#c5ff41", border: "1px solid #c5ff41", padding: "9px 13px", borderRadius: "6px", textDecoration: "none" }}>
              Open →
            </Link>
          </div>
        ))}
      </div>
      <p style={{ textAlign: "center", color: "#788078", marginTop: "45px" }}>FitForge AI · Your personal fitness companion</p>
    </div>
  );
}

function FeaturePage({ title, description }) {
  const navigate = useNavigate();

  return (
    <div style={{ minHeight: "100vh", background: "#0b0e0c", color: "#f4f6f4", padding: "40px", fontFamily: "Arial, sans-serif" }}>
      <h2 style={{ color: "#c5ff41" }}>FITFORGE AI</h2>
      <h1>{title}</h1>
      <p style={{ color: "#a5aca6" }}>{description}</p>
      <button onClick={() => navigate("/dashboard")} style={{ background: "#c5ff41", padding: "10px 18px", border: "none", borderRadius: "7px", cursor: "pointer" }}>
        ← Back to Dashboard
      </button>
    </div>
  );
}

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/bmi" element={<BMI />} />
        <Route path="/calories" element={<Calories />} />
        <Route path="/workouts" element={<Workouts />} />
        <Route path="/nutrition" element={<Nutrition />} />
        <Route path="/progress" element={<Progress />} />
        <Route
  path="/recommendations"
  element={<Recommendations />}
/>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
