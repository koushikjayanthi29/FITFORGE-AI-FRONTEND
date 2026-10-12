
import { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function Calories() {
  const navigate = useNavigate();
  const [age, setAge] = useState("");
  const [gender, setGender] = useState("male");
  const [height, setHeight] = useState("");
  const [weight, setWeight] = useState("");
  const [activity, setActivity] = useState("1.375");
  const [goal, setGoal] = useState("maintain");
  const [result, setResult] = useState(null);

  function calculate(e) {
    e.preventDefault();

    const h = Number(height);
    const w = Number(weight);
    const a = Number(age);

    if (a <= 0 || h <= 0 || w <= 0) {
      setResult({ error: "Enter valid age, height and weight." });
      return;
    }

    const bmr = gender === "male"
      ? 10 * w + 6.25 * h - 5 * a + 5
      : 10 * w + 6.25 * h - 5 * a - 161;

    const maintenance = bmr * Number(activity);
    const target = goal === "lose"
      ? maintenance - 500
      : goal === "gain"
        ? maintenance + 300
        : maintenance;

    setResult({
      bmr: Math.round(bmr),
      maintenance: Math.round(maintenance),
      target: Math.round(target)
    });
  }

  const inputStyle = {
    display: "block",
    width: "100%",
    boxSizing: "border-box",
    padding: "12px",
    margin: "8px 0 18px",
    background: "#0b0e0c",
    color: "white",
    border: "1px solid #414b41",
    borderRadius: "6px"
  };

  const buttonStyle = {
    padding: "12px 18px",
    margin: "8px",
    background: "#c5ff41",
    color: "#10130d",
    border: "none",
    borderRadius: "7px",
    cursor: "pointer",
    fontWeight: "bold"
  };

  return (
    <div style={{
      minHeight: "100vh",
      background: "#0b0e0c",
      color: "#f4f6f4",
      padding: "30px",
      textAlign: "center",
      fontFamily: "Arial, sans-serif"
    }}>
      <h2 style={{ color: "#c5ff41" }}>FITFORGE AI</h2>
      <h1>Calories Tracker</h1>
      <p style={{ color: "#a5aca6" }}>
        Calculate your daily calorie requirements.
      </p>

      <form onSubmit={calculate} style={{
        maxWidth: "430px",
        margin: "25px auto",
        padding: "25px",
        background: "#151a16",
        border: "1px solid #303a30",
        borderRadius: "14px",
        textAlign: "left"
      }}>
        <label>Age (years)</label>
        <input type="number" min="1" value={age}
          onChange={e => setAge(e.target.value)}
          required style={inputStyle} />

        <label>Gender</label>
        <select value={gender} onChange={e => setGender(e.target.value)}
          style={inputStyle}>
          <option value="male">Male</option>
          <option value="female">Female</option>
        </select>

        <label>Height (cm)</label>
        <input type="number" min="1" value={height}
          onChange={e => setHeight(e.target.value)}
          required style={inputStyle} />

        <label>Weight (kg)</label>
        <input type="number" min="1" value={weight}
          onChange={e => setWeight(e.target.value)}
          required style={inputStyle} />

        <label>Activity Level</label>
        <select value={activity} onChange={e => setActivity(e.target.value)}
          style={inputStyle}>
          <option value="1.2">Sedentary — little exercise</option>
          <option value="1.375">Lightly active</option>
          <option value="1.55">Moderately active</option>
          <option value="1.725">Very active</option>
          <option value="1.9">Extremely active</option>
        </select>

        <label>Fitness Goal</label>
        <select value={goal} onChange={e => setGoal(e.target.value)}
          style={inputStyle}>
          <option value="lose">Lose weight</option>
          <option value="maintain">Maintain weight</option>
          <option value="gain">Gain weight</option>
        </select>

        <button type="submit" style={buttonStyle}>
          Calculate Calories
        </button>

        {result && (
          <div style={{
            marginTop: "20px",
            padding: "15px",
            background: "#0b0e0c",
            borderRadius: "8px"
          }}>
            {result.error ? <p>{result.error}</p> : (
              <>
                <h3 style={{ color: "#c5ff41" }}>Daily Results</h3>
                <p>BMR: {result.bmr} kcal/day</p>
                <p>Maintenance: {result.maintenance} kcal/day</p>
                <h2 style={{ color: "#c5ff41" }}>
                  Target: {result.target} kcal/day
                </h2>
                <p style={{ color: "#a5aca6", fontSize: "12px" }}>
                  Estimate only; actual calorie needs vary by individual.
                </p>
              </>
            )}
          </div>
        )}
      </form>

      <button onClick={() => navigate("/dashboard")} style={buttonStyle}>
        ← Back to Dashboard
      </button>
    </div>
  );
}
