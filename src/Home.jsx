import { Link } from "react-router-dom";
import "./App.css";

function Home() {
  return (
    <div className="app">
      <aside className="sidebar">
        <h2 className="logo">FITFORGE<span> AI</span></h2>

        <p className="menu-title">MENU</p>
        <nav>
          <a className="active" href="#dashboard">▦ Dashboard</a>
          <a href="#profile">♙ Fitness Profile</a>
          <a href="#workouts">🏋 Workouts</a>
          <a href="#nutrition">🥗 Nutrition</a>
          <a href="#progress">📈 Progress</a>
          <a href="#recommendations">✦ AI Recommendations</a>
          <a href="#trainers">♧ Trainers</a>
        </nav>

        <div className="sidebar-bottom">
          <p>YOUR FITNESS JOURNEY</p>
          <h3>Build a stronger you.</h3>
          <span>Small steps. Big results.</span>
        </div>
      </aside>

      <main className="main">
        <header className="topbar">
          <div>
            <p className="eyebrow">YOUR PERSONAL FITNESS ASSISTANT</p>
            <h1>Welcome to FitForge AI <span>✦</span></h1>
          </div>
          <div style={{ display: "flex", alignItems: "center", gap: "12px" }}>
  <Link to="/login" style={{ color: "#c5ff41", textDecoration: "none" }}>
    Login
  </Link>

  <Link
    to="/register"
    style={{
      background: "#c5ff41",
      color: "#0b0e0c",
      padding: "10px 16px",
      borderRadius: "8px",
      textDecoration: "none",
      fontWeight: "bold",
    }}
  >
    Register
  </Link>

  <div className="avatar">K</div>
</div>
        </header>

        <section className="hero" id="dashboard">
          <div className="hero-content">
            <span className="pill">✦ YOUR FITNESS, REIMAGINED</span>
            <h2>Forge your<br />stronger self.</h2>
            <p>
              Your goals. Your plan. Your progress.
              Everything you need to become your best self.
            </p>
            <Link className="primary-btn" to="/register">
  Get Started <span>→</span>
</Link>
          </div>
          <div className="hero-art">
            <div className="hero-circle"></div>
            <div className="hero-emoji">🏋️</div>
            <div className="floating-tag">✦ Stronger every day</div>
          </div>
        </section>

        <section className="section-heading">
          <div>
            <h2>Your fitness overview</h2>
            <p>Your journey starts with understanding your body.</p>
          </div>
          <span className="live-status">● READY TO BEGIN</span>
        </section>

        <section className="stats">
          <article className="stat-card">
            <div className="stat-icon">⚖</div>
            <p>BODY MASS INDEX</p>
            <h3>-- <small>BMI</small></h3>
            <span>Complete your fitness profile</span>
          </article>
          <article className="stat-card">
            <div className="stat-icon">🔥</div>
            <p>DAILY CALORIES</p>
            <h3>-- <small>kcal</small></h3>
            <span>Your daily target</span>
          </article>
          <article className="stat-card">
            <div className="stat-icon">💪</div>
            <p>WORKOUTS</p>
            <h3>-- <small>plans</small></h3>
            <span>Your personalized routine</span>
          </article>
          <article className="stat-card">
            <div className="stat-icon">📈</div>
            <p>PROGRESS</p>
            <h3>Start <small>today</small></h3>
            <span>Every step counts</span>
          </article>
        </section>

        <section className="bottom-grid">
          <article className="panel" id="profile">
            <span className="panel-label">STEP 01</span>
            <h2>Set your foundation</h2>
            <p>Tell us about your fitness level, body measurements and goals.</p>
            <a href="#profile">Complete fitness profile →</a>
          </article>
          <article className="panel accent-panel" id="recommendations">
            <span className="panel-label">POWERED BY YOUR GOALS</span>
            <h2>Your next level awaits.</h2>
            <p>Discover workout and nutrition recommendations tailored to you.</p>
            <a href="#workouts">Explore your plan →</a>
          </article>
        </section>

        <footer>FITFORGE AI © 2026 · YOUR JOURNEY, YOUR STRENGTH.</footer>
      </main>
    </div>
  );
}

export default Home;
