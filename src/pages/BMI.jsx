
import { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function BMI() {
  const navigate = useNavigate();
  const [height, setHeight] = useState("");
  const [weight, setWeight] = useState("");
  const [result, setResult] = useState(null);

  const calculateBMI = (e) => {
    e.preventDefault();

    const h = Number(height) / 100;
    const w = Number(weight);

    if (h <= 0 || w <= 0 || !height || !weight) {
      setResult({ error: "Enter a valid height and weight." });
      return;
    }

    const bmi = w / (h * h);
    let category = "";

    if (bmi < 18.5) category = "Underweight";
    else if (bmi < 25) category = "Normal weight";
    else if (bmi < 30) category = "Overweight";
    else category = "Obese";

    setResult({ bmi: bmi.toFixed(2), category });
  };

  return (
    <div style={{
      minHeight: "100vh",
      background: "#0b0e0c",
      color: "#f4f6f4",
      padding: "35px",
      textAlign: "center",
      fontFamily: "Arial, sans-serif"
    }}>
      <h2 style={{ color: "#c5ff41" }}>FITFORGE AI</h2>
      <h1>BMI Calculator</h1>
      <p style={{ color: "#a5aca6" }}>
        Calculate your Body Mass Index
      </p>

      <form onSubmit={calculateBMI} style={{
        maxWidth: "400px",
        margin: "30px auto",
        padding: "25px",
        background: "#151a16",
        border: "1px solid #303a30",
        borderRadius: "14px"
      }}>
        <label>Height (cm)</label>
        <input
          type="number"
          value={height}
          onChange={(e) => setHeight(e.target.value)}
          placeholder="Example: 170"
          required
          min="1"
          style={inputStyle}
        />

        <label>Weight (kg)</label>
        <input
          type="number"
          value={weight}
          onChange={(e) => setWeight(e.target.value)}
          placeholder="Example: 70"
          required
          min="1"
          style={inputStyle}
        />

        <button type="submit" style={buttonStyle}>
          Calculate BMI
        </button>

        {result && (
          <div style={{ marginTop: "20px" }}>
            {result.error ? (
              <p>{result.error}</p>
            ) : (
              <>
                <h2 style={{ color: "#c5ff41" }}>
                  BMI: {result.bmi}
                </h2>
                <h3>{result.category}</h3>
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

const inputStyle = {
  display: "block",
  width: "100%",
  boxSizing: "border-box",
  padding: "12px",
  margin: "10px 0 20px",
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
