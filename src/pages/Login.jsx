
import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import axios from "axios";
import "./Auth.css";

function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    setError("");
    setLoading(true);

    try {
      const response = await axios.post(
        "http://localhost:8080/api/auth/login",
        { email, password }
      );
console.log("Login response:", response.data);

      localStorage.setItem("token", response.data.token);
localStorage.setItem("userName", response.data.name || "");
localStorage.setItem("userEmail", response.data.email || email);
const userId =
  response.data.userId ??
  response.data.id ??
  response.data.user?.id;

if (userId != null) {
  localStorage.setItem("userId", String(userId));
} else {
  localStorage.removeItem("userId");
  console.error("Login response does not contain a user ID.");
}
      navigate("/dashboard");
    } catch (err) {
      setError(
        err.response?.data?.message ||
        "Login failed. Check your email and password, then try again."
      );
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="auth-page">
      <Link to="/" className="auth-logo">FITFORGE <span>AI</span></Link>

      <div className="auth-card">
        <span className="auth-tag">WELCOME BACK</span>
        <h1>Ready to get stronger?</h1>
        <p className="auth-subtitle">Sign in to continue your fitness journey.</p>

        <form onSubmit={handleLogin}>
          <label htmlFor="email">Email address</label>
          <input
            id="email"
            type="email"
            placeholder="you@example.com"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />

          <label htmlFor="password">Password</label>
          <input
            id="password"
            type="password"
            placeholder="Enter your password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />

          {error && <div className="auth-error">{error}</div>}

          <button type="submit" disabled={loading}>
            {loading ? "Signing in..." : "Sign In →"}
          </button>
        </form>

        <p className="auth-switch">
          New to FitForge AI? <Link to="/register">Create an account</Link>
        </p>
      </div>
    </div>
  );
}

export default Login;
