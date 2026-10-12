
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const API = "http://localhost:8080/api/progress";
const USER_ID = localStorage.getItem("userId");
export default function Progress() {
  const navigate = useNavigate();

  const [records, setRecords] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");
  const [saving, setSaving] = useState(false);

  const [form, setForm] = useState({
    weight: "",
    bodyFat: "",
    muscleMass: "",
    notes: "",
  });

const loadProgress = async () => {
  if (!USER_ID) {
    setError("User ID not found. Please log in again.");
    setLoading(false);
    return;
  }

  setLoading(true);
  setError("");

  try {
    const response = await axios.get(
      `${API}/user/${USER_ID}`
    );

    setRecords(Array.isArray(response.data) ? response.data : []);
  } catch (err) {
    console.error("Progress loading error:", err);
    setError("Unable to load progress. Check the backend and user ID.");
  } finally {
    setLoading(false);
  }
};

  useEffect(() => {
    loadProgress();
  }, []);

  const handleChange = (event) => {
    setForm({ ...form, [event.target.name]: event.target.value });
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    setError("");

    if (!form.weight || Number(form.weight) <= 0) {
      setError("Please enter a valid weight.");
      return;
    }

    const payload = {
      weight: Number(form.weight),
      bodyFat: form.bodyFat === "" ? null : Number(form.bodyFat),
      muscleMass:
        form.muscleMass === "" ? null : Number(form.muscleMass),
      notes: form.notes.trim(),
    };

    setSaving(true);

    try {
      await axios.post(`${API}/${USER_ID}`, payload);

      setForm({
        weight: "",
        bodyFat: "",
        muscleMass: "",
        notes: "",
      });

      await loadProgress();
    } catch (err) {
      console.error("Progress saving error:", err);
      setError(
        "Unable to save progress. Your backend may require different field names or a user ID."
      );
    } finally {
      setSaving(false);
    }
  };

  const deleteRecord = async (id) => {
    if (id == null) {
      setError("This progress record has no ID, so it cannot be deleted.");
      return;
    }

    if (!window.confirm("Delete this progress record?")) return;

    try {
      await axios.delete(`${API}/${id}`);
      await loadProgress();
    } catch (err) {
      console.error("Progress deletion error:", err);
      setError("Unable to delete this record. Check your backend endpoint.");
    }
  };

  const latest = records.length
    ? [...records].sort(
        (a, b) =>
          new Date(b.date || b.createdAt || 0) -
          new Date(a.date || a.createdAt || 0)
      )[0]
    : null;

  const oldest = records.length
    ? [...records].sort(
        (a, b) =>
          new Date(a.date || a.createdAt || 0) -
          new Date(b.date || b.createdAt || 0)
      )[0]
    : null;

  const weightChange =
    latest && oldest &&
    latest.weight != null &&
    oldest.weight != null
      ? Number(latest.weight) - Number(oldest.weight)
      : null;

  const styles = {
    page: {
      minHeight: "100vh",
      background: "#0b0e0c",
      color: "#f5f5f5",
      padding: "30px 20px",
      fontFamily: "Arial, sans-serif",
    },
    container: {
      maxWidth: "1050px",
      margin: "0 auto",
    },
    back: {
      padding: "10px 16px",
      background: "#20251f",
      border: "1px solid #343b30",
      borderRadius: "8px",
      color: "#c5ff41",
      cursor: "pointer",
      marginBottom: "24px",
    },
    heading: {
      color: "#c5ff41",
      fontSize: "32px",
      marginBottom: "8px",
    },
    subtitle: {
      color: "#a3a3a3",
      lineHeight: 1.6,
      marginBottom: "26px",
    },
    grid: {
      display: "grid",
      gridTemplateColumns: "repeat(auto-fit, minmax(210px, 1fr))",
      gap: "16px",
      marginBottom: "28px",
    },
    card: {
      background: "#151a16",
      border: "1px solid #2b332b",
      borderRadius: "12px",
      padding: "20px",
    },
    section: {
      background: "#151a16",
      border: "1px solid #2b332b",
      borderRadius: "14px",
      padding: "22px",
      marginBottom: "24px",
    },
    label: {
      display: "block",
      color: "#b8beb8",
      fontSize: "14px",
      marginBottom: "8px",
    },
    input: {
      width: "100%",
      boxSizing: "border-box",
      padding: "12px",
      background: "#0e120f",
      color: "#fff",
      border: "1px solid #394238",
      borderRadius: "8px",
      outline: "none",
    },
    button: {
      padding: "12px 18px",
      background: "#c5ff41",
      color: "#10140d",
      border: "none",
      borderRadius: "8px",
      fontWeight: "bold",
      cursor: "pointer",
    },
    muted: {
      color: "#a3a3a3",
      fontSize: "13px",
      lineHeight: 1.6,
    },
    record: {
      display: "flex",
      justifyContent: "space-between",
      alignItems: "center",
      gap: "16px",
      flexWrap: "wrap",
      padding: "16px 0",
      borderBottom: "1px solid #2b332b",
    },
    delete: {
      background: "#30201f",
      color: "#ff9990",
      border: "1px solid #55312d",
      padding: "8px 12px",
      borderRadius: "7px",
      cursor: "pointer",
    },
  };

  return (
    <div style={styles.page}>
      <div style={styles.container}>
        <button
          style={styles.back}
          onClick={() => navigate("/dashboard")}
        >
          ← Back to Dashboard
        </button>

        <h1 style={styles.heading}>Progress Tracking</h1>
        <p style={styles.subtitle}>
          Record your fitness measurements, track your weight, and monitor
          your progress over time.
        </p>

        {error && (
          <div
            role="alert"
            style={{
              padding: "14px",
              marginBottom: "20px",
              borderRadius: "8px",
              color: "#ffaaa0",
              background: "#301c1b",
              overflowWrap: "anywhere",
            }}
          >
            {error}
          </div>
        )}

        <div style={styles.grid}>
          <div style={styles.card}>
            <p style={styles.muted}>LATEST WEIGHT</p>
            <h2 style={{ color: "#c5ff41" }}>
              {latest?.weight != null ? `${latest.weight} kg` : "--"}
            </h2>
          </div>

          <div style={styles.card}>
            <p style={styles.muted}>WEIGHT CHANGE</p>
            <h2
              style={{
                color:
                  weightChange == null
                    ? "#c5ff41"
                    : weightChange > 0
                    ? "#ffbd69"
                    : weightChange < 0
                    ? "#c5ff41"
                    : "#f5f5f5",
              }}
            >
              {weightChange == null
                ? "--"
                : `${weightChange > 0 ? "+" : ""}${weightChange.toFixed(1)} kg`}
            </h2>
            <p style={styles.muted}>
              Difference between oldest and latest records
            </p>
          </div>

          <div style={styles.card}>
            <p style={styles.muted}>TOTAL RECORDS</p>
            <h2 style={{ color: "#c5ff41" }}>{records.length}</h2>
          </div>
        </div>

        <div style={styles.section}>
          <h2 style={{ marginTop: 0 }}>Add Progress</h2>
          <p style={styles.muted}>
            Enter your latest measurements below.
          </p>

          <form onSubmit={handleSubmit}>
            <div style={styles.grid}>
              <div>
                <label style={styles.label} htmlFor="weight">
                  Weight (kg) *
                </label>
                <input
                  id="weight"
                  style={styles.input}
                  type="number"
                  name="weight"
                  min="1"
                  max="500"
                  step="0.1"
                  placeholder="e.g. 70"
                  value={form.weight}
                  onChange={handleChange}
                  required
                />
              </div>

              <div>
                <label style={styles.label} htmlFor="bodyFat">
                  Body Fat (%)
                </label>
                <input
                  id="bodyFat"
                  style={styles.input}
                  type="number"
                  name="bodyFat"
                  min="0"
                  max="100"
                  step="0.1"
                  placeholder="e.g. 20"
                  value={form.bodyFat}
                  onChange={handleChange}
                />
              </div>

              <div>
                <label style={styles.label} htmlFor="muscleMass">
                  Muscle Mass (kg)
                </label>
                <input
                  id="muscleMass"
                  style={styles.input}
                  type="number"
                  name="muscleMass"
                  min="0"
                  step="0.1"
                  placeholder="e.g. 30"
                  value={form.muscleMass}
                  onChange={handleChange}
                />
              </div>

              <div>
                <label style={styles.label} htmlFor="notes">
                  Notes
                </label>
                <input
                  id="notes"
                  style={styles.input}
                  type="text"
                  name="notes"
                  placeholder="e.g. Completed 4 workouts"
                  value={form.notes}
                  onChange={handleChange}
                />
              </div>
            </div>

            <button style={styles.button} type="submit" disabled={saving}>
              {saving ? "Saving..." : "+ Save Progress"}
            </button>
          </form>
        </div>

        <div style={styles.section}>
          <div
            style={{
              display: "flex",
              justifyContent: "space-between",
              alignItems: "center",
              gap: "12px",
              flexWrap: "wrap",
            }}
          >
            <h2 style={{ marginTop: 0 }}>Progress History</h2>
            <button
              style={styles.back}
              onClick={loadProgress}
              disabled={loading}
            >
              Refresh
            </button>
          </div>

          {loading ? (
            <p style={styles.muted}>Loading progress history...</p>
          ) : records.length === 0 ? (
            <p style={styles.muted}>
              No progress records yet. Add your first record above.
            </p>
          ) : (
            [...records]
              .sort(
                (a, b) =>
                  new Date(b.date || b.createdAt || 0) -
                  new Date(a.date || a.createdAt || 0)
              )
              .map((record, index) => (
                <div
                  style={styles.record}
                  key={record.id ?? index}
                >
                  <div>
                    <h3 style={{ color: "#c5ff41", margin: "0 0 8px" }}>
                      {record.weight ?? "--"} kg
                    </h3>

                    <p style={styles.muted}>
                      {record.date || record.createdAt
                        ? new Date(
                            record.date || record.createdAt
                          ).toLocaleDateString()
                        : "Date not provided"}
                    </p>

                    <p style={styles.muted}>
                      Body fat: {record.bodyFat ?? "--"}%
                      {" · "}
                      Muscle mass: {record.muscleMass ?? "--"} kg
                    </p>

                    {record.notes && (
                      <p style={{ color: "#ddd" }}>{record.notes}</p>
                    )}
                  </div>

                  <button
                    style={styles.delete}
                    onClick={() => deleteRecord(record.id)}
                  >
                    Delete
                  </button>
                </div>
              ))
          )}
        </div>
      </div>
    </div>
  );
}
