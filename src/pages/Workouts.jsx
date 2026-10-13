
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { API_BASE_URL } from "../services/api";

export default function Workouts() {
  const navigate = useNavigate();
  const [workoutData, setWorkoutData] = useState([]);
  const [filter, setFilter] = useState("All");
  const [completed, setCompleted] = useState([]);
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    axios
      .get(`${API_BASE_URL}/api/workouts`)
      .then((res) => {
        setWorkoutData(Array.isArray(res.data) ? res.data : []);
      })
      .catch((err) => {
        console.error("Workout API error:", err);
        setError("Unable to load workouts. Check that the backend is running.");
      })
      .finally(() => setLoading(false));
  }, []);

  const filtered =
    filter === "All"
      ? workoutData
      : workoutData.filter(
          (w) =>
            (w.difficulty ?? w.level ?? "").toLowerCase() ===
            filter.toLowerCase()
        );

  return (
    <div style={pageStyle}>
      <header style={headerStyle}>
        <h2 style={{ color: "#c5ff41" }}>FITFORGE AI</h2>
        <button style={btn} onClick={() => navigate("/dashboard")}>
          ← Dashboard
        </button>
      </header>

      <h1 style={{ textAlign: "center" }}>Workout Plans 🏋️</h1>
      <p style={{ textAlign: "center", color: "#a5aca6" }}>
        Explore exercises and track your workout completion.
      </p>

      <div style={{ textAlign: "center", margin: "25px" }}>
        <label htmlFor="level">Filter by level: </label>{" "}
        <select
          id="level"
          value={filter}
          onChange={(e) => setFilter(e.target.value)}
          style={selectStyle}
        >
          <option>All</option>
          <option>Beginner</option>
          <option>Intermediate</option>
          <option>Advanced</option>
        </select>
      </div>

      <p style={{ textAlign: "center", color: "#c5ff41" }}>
        Completed: {completed.length} / {workoutData.length}
      </p>

      {loading && <p style={messageStyle}>Loading workouts...</p>}
      {error && <p style={{ ...messageStyle, color: "#ff7777" }}>{error}</p>}

      {!loading && !error && workoutData.length === 0 && (
        <p style={messageStyle}>No workout plans found.</p>
      )}

      <div style={gridStyle}>
        {filtered.map((w, index) => {
          const id = w.id ?? index;
          const name = w.workoutName ?? w.name ?? w.exerciseName ?? "Workout";
          const isCompleted = completed.includes(id);

          return (
            <div key={id} style={cardStyle}>
              <h2 style={{ color: "#c5ff41" }}>{name}</h2>
              <p>Exercise: {w.exerciseName ?? name}</p>
              <p>Target muscle: {w.targetMuscle ?? w.muscle ?? "—"}</p>
              <p>Sets: {w.sets ?? "—"}</p>
              <p>Repetitions: {w.repetitions ?? w.reps ?? "—"}</p>
              <p>Duration: {w.duration ?? "—"}</p>
              <p>Level: {w.difficulty ?? w.level ?? "—"}</p>

              <button
                style={{
                  ...btn,
                  background: isCompleted ? "#303a30" : "#c5ff41",
                  color: isCompleted ? "white" : "#10130d",
                }}
                onClick={() =>
                  setCompleted((prev) =>
                    isCompleted
                      ? prev.filter((item) => item !== id)
                      : [...prev, id]
                  )
                }
              >
                {isCompleted ? "✓ Completed" : "Mark Complete"}
              </button>
            </div>
          );
        })}
      </div>
    </div>
  );
}

const pageStyle = {
  minHeight: "100vh",
  background: "#0b0e0c",
  color: "#f4f6f4",
  padding: "30px",
  fontFamily: "Arial, sans-serif",
};

const headerStyle = {
  display: "flex",
  justifyContent: "space-between",
  alignItems: "center",
  flexWrap: "wrap",
  gap: "15px",
};

const btn = {
  padding: "10px 16px",
  background: "#c5ff41",
  color: "#10130d",
  border: "none",
  borderRadius: "7px",
  cursor: "pointer",
  fontWeight: "bold",
};

const selectStyle = {
  padding: "10px",
  background: "#151a16",
  color: "white",
  border: "1px solid #c5ff41",
  borderRadius: "6px",
};

const gridStyle = {
  display: "grid",
  gridTemplateColumns: "repeat(auto-fit, minmax(230px, 1fr))",
  gap: "18px",
};

const cardStyle = {
  background: "#151a16",
  border: "1px solid #303a30",
  borderRadius: "12px",
  padding: "20px",
};

const messageStyle = {
  textAlign: "center",
  margin: "25px",
};
