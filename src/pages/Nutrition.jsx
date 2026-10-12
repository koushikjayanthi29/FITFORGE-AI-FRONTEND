
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

export default function Nutrition() {
  const navigate = useNavigate();

  const [plans, setPlans] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  const meals = [
    {
      name: "Breakfast",
      time: "7:30 AM",
      description: "Start your day with energy",
      icon: "🌅",
      color: "#fbbf24",
    },
    {
      name: "Lunch",
      time: "1:00 PM",
      description: "A balanced and filling meal",
      icon: "🍱",
      color: "#34d399",
    },
    {
      name: "Pre-Workout",
      time: "4:30 PM",
      description: "Fuel your workout session",
      icon: "⚡",
      color: "#c5ff41",
    },
    {
      name: "Dinner",
      time: "8:00 PM",
      description: "Recover and refuel your body",
      icon: "🌙",
      color: "#a78bfa",
    },
  ];

  useEffect(() => {
    axios
      .get("http://localhost:8080/api/nutrition")
      .then((response) => {
        const data = response.data;
        setPlans(Array.isArray(data) ? data : []);
      })
      .catch((err) => {
        console.error("Nutrition loading error:", err);
        setError(
          "Unable to load nutrition plans. Please check whether the backend is running."
        );
      })
      .finally(() => setLoading(false));
  }, []);

  const getMealPlan = (mealName) => {
    return plans.filter(
      (plan) =>
        (plan.mealName || "").toLowerCase().replace(/[-_]/g, " ").trim() ===
        mealName.toLowerCase().replace(/[-_]/g, " ").trim()
    );
  };

  const getValue = (plan, key) => plan[key] ?? 0;

  const totalFor = (key) =>
    meals.reduce(
      (sum, meal) =>
        sum +
        getMealPlan(meal.name).reduce(
          (mealSum, plan) => mealSum + Number(getValue(plan, key)),
          0
        ),
      0
    );

  const styles = {
    page: {
      minHeight: "100vh",
      background: "#0b0e0c",
      color: "#f5f5f5",
      padding: "32px 20px",
      fontFamily: "Arial, sans-serif",
    },
    container: {
      maxWidth: "1100px",
      margin: "0 auto",
    },
    back: {
      background: "#20251f",
      color: "#c5ff41",
      border: "1px solid #343b30",
      borderRadius: "8px",
      padding: "10px 16px",
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
      marginBottom: "28px",
      lineHeight: 1.6,
    },
    summaryGrid: {
      display: "grid",
      gridTemplateColumns: "repeat(auto-fit, minmax(150px, 1fr))",
      gap: "14px",
      marginBottom: "32px",
    },
    summaryCard: {
      background: "#151a16",
      border: "1px solid #2b332b",
      borderRadius: "12px",
      padding: "18px",
    },
    mealGrid: {
      display: "grid",
      gridTemplateColumns: "repeat(auto-fit, minmax(240px, 1fr))",
      gap: "18px",
    },
    mealCard: {
      background: "#151a16",
      border: "1px solid #2b332b",
      borderRadius: "14px",
      padding: "22px",
    },
    mealTitle: {
      fontSize: "21px",
      margin: "0 0 6px",
    },
    muted: {
      color: "#a3a3a3",
      fontSize: "13px",
      lineHeight: 1.6,
    },
    foodBox: {
      background: "#0e120f",
      borderRadius: "8px",
      padding: "12px",
      marginTop: "16px",
      lineHeight: 1.7,
      overflowWrap: "anywhere",
    },
    macroGrid: {
      display: "grid",
      gridTemplateColumns: "repeat(2, minmax(0, 1fr))",
      gap: "10px",
      marginTop: "16px",
    },
    macro: {
      background: "#20251f",
      borderRadius: "8px",
      padding: "10px",
    },
  };

  return (
    <div style={styles.page}>
      <div style={styles.container}>
        <button style={styles.back} onClick={() => navigate("/dashboard")}>
          ← Back to Dashboard
        </button>

        <h1 style={styles.heading}>Nutrition Plans</h1>
        <p style={styles.subtitle}>
          Your daily nutrition schedule — breakfast, lunch, pre-workout, and
          dinner.
        </p>

        {loading ? (
          <p style={{ color: "#c5ff41" }}>Loading nutrition plans...</p>
        ) : error ? (
          <div>
            <p style={{ color: "#ff7777" }}>{error}</p>
            <button style={styles.back} onClick={() => window.location.reload()}>
              Retry
            </button>
          </div>
        ) : (
          <>
            <div style={styles.summaryGrid}>
              {[
                ["Calories", totalFor("calories"), "kcal"],
                ["Protein", totalFor("protein"), "g"],
                ["Carbs", totalFor("carbohydrates"), "g"],
                ["Fats", totalFor("fats"), "g"],
              ].map(([label, value, unit]) => (
                <div style={styles.summaryCard} key={label}>
                  <p style={styles.muted}>{`TOTAL ${label.toUpperCase()}`}</p>
                  <h2 style={{ color: "#c5ff41", margin: "8px 0" }}>
                    {Math.round(value)}{" "}
                    <span style={{ fontSize: "13px", color: "#a3a3a3" }}>
                      {unit}
                    </span>
                  </h2>
                </div>
              ))}
            </div>

            <div style={styles.mealGrid}>
              {meals.map((meal) => {
                const mealPlans = getMealPlan(meal.name);

                return (
                  <div style={styles.mealCard} key={meal.name}>
                    <div style={{ fontSize: "30px", marginBottom: "12px" }}>
                      {meal.icon}
                    </div>

                    <h2 style={{ ...styles.mealTitle, color: meal.color }}>
                      {meal.name}
                    </h2>

                    <p style={styles.muted}>
                      {meal.time} · {meal.description}
                    </p>

                    {mealPlans.length > 0 ? (
                      mealPlans.map((plan, index) => (
                        <div key={plan.id ?? index}>
                          <div style={styles.foodBox}>
                            <strong style={{ color: "#c5ff41" }}>
                              {plan.mealName || meal.name}
                            </strong>
                            <p style={{ marginBottom: 0 }}>
                              {plan.foodItems || "Food details not provided"}
                            </p>
                            {plan.goal && (
                              <p style={styles.muted}>
                                Goal: {plan.goal}
                              </p>
                            )}
                          </div>

                          <div style={styles.macroGrid}>
                            {[
                              ["Calories", "calories", "kcal"],
                              ["Protein", "protein", "g"],
                              ["Carbs", "carbohydrates", "g"],
                              ["Fats", "fats", "g"],
                            ].map(([label, key, unit]) => (
                              <div style={styles.macro} key={key}>
                                <p style={styles.muted}>{label}</p>
                                <strong>
                                  {getValue(plan, key)} {unit}
                                </strong>
                              </div>
                            ))}
                          </div>
                        </div>
                      ))
                    ) : (
                      <div style={styles.foodBox}>
                        <p style={{ color: "#c5ff41", marginTop: 0 }}>
                          {meal.name} plan
                        </p>
                        <p style={styles.muted}>
                          No {meal.name.toLowerCase()} plan found in the
                          database yet. Add a nutrition record for this meal
                          using the exact meal name.
                        </p>
                      </div>
                    )}
                  </div>
                );
              })}
            </div>

            <p style={{ ...styles.muted, marginTop: "24px" }}>
              Daily totals are calculated from the nutrition records loaded
              from your backend. Only meals saved in the database are included.
            </p>
          </>
        )}
      </div>
    </div>
  );
}
