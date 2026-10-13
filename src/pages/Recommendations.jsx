
import { useEffect, useState } from "react";
import axios from "axios";
import { API_BASE_URL } from "../services/api";
import "./Recommendations.css";

const PROFILE_ID = 1;
const API_URL = `${API_BASE_URL}/api/workout-recommendations/${PROFILE_ID}`;

function Recommendations() {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    const fetchRecommendations = async () => {
      try {
        const token = localStorage.getItem("token");

        const response = await axios.get(API_URL, {
          headers: token
            ? { Authorization: `Bearer ${token}` }
            : {},
        });

        setData(response.data);
      } catch (err) {
        console.error("Recommendations error:", err);
        setError(
          `Unable to load recommendations (HTTP ${
            err.response?.status || "connection error"
          }).`
        );
      } finally {
        setLoading(false);
      }
    };

    fetchRecommendations();
  }, []);

  const workouts = Array.isArray(data?.workouts)
    ? data.workouts
    : [];

  const getValue = (workout, keys) => {
    for (const key of keys) {
      if (workout[key] != null && workout[key] !== "") {
        return workout[key];
      }
    }
    return null;
  };

  return (
    <div className="recommendations-page">
      <header className="recommendations-header">
        <p className="recommendations-label">FITFORGE AI</p>
        <h1>Personalized Recommendations</h1>
        <p>
          Your fitness journey, with recommendations
          to help you train smarter.
        </p>
      </header>

      {loading && (
        <p className="recommendations-message">
          Loading your workouts...
        </p>
      )}

      {error && (
        <div className="recommendations-error">{error}</div>
      )}

      {!loading && !error && data && (
        <>
          <section className="recommendations-summary">
            <div>
              <span>FITNESS GOAL</span>
              <h2>{data.goal || "Not specified"}</h2>
            </div>

            <div>
              <span>FITNESS LEVEL</span>
              <h2>{data.fitnessLevel || "Not specified"}</h2>
            </div>

            <div>
              <span>TOTAL WORKOUTS</span>
              <h2>{data.totalWorkouts ?? workouts.length}</h2>
            </div>
          </section>

          <h2 className="workouts-heading">
            Recommended Workouts
          </h2>

          {workouts.length === 0 ? (
            <div className="recommendations-empty">
              No workouts match your current fitness level.
              Check the workout data in your database.
            </div>
          ) : (
            <div className="recommendations-grid">
              {workouts.map((workout, index) => {
                const name =
  workout.exerciseName ||
  workout.workout_name ||
  workout.workoutName ||
  workout.name ||
  "Workout";
                const duration = getValue(workout, [
                  "duration",
                  "durationMinutes",
                ]);

                const difficulty = getValue(workout, [
                  "difficulty",
                  "fitnessLevel",
                ]);

                const description = getValue(workout, [
                  "description",
                  "instructions",
                ]);

                const sets = getValue(workout, ["sets"]);
                const reps = getValue(workout, [
                  "reps",
                  "repetitions",
                ]);

                return (
                  <article
                    className="recommendation-card"
                    key={workout.id ?? index}
                  >
                    <span className="recommendation-number">
                      RECOMMENDATION {index + 1}
                    </span>

                    <h2>{name}</h2>

{workout.targetMuscle && (
  <p>
    <strong>Target Muscle:</strong>{" "}
    {workout.targetMuscle}
  </p>
)}

                    {description && <p>{description}</p>}

                    <div className="workout-details">
                      {duration != null && (
                        <p>
                          <strong>Duration:</strong>{" "}
                          {duration}
                          {typeof duration === "number"
                            ? " minutes"
                            : ""}
                        </p>
                      )}

                      {sets != null && (
                        <p>
                          <strong>Sets:</strong> {sets}
                        </p>
                      )}

                      {reps != null && (
                        <p>
                          <strong>Repetitions:</strong> {reps}
                        </p>
                      )}

                      {difficulty && (
                        <p>
                          <strong>Difficulty:</strong>{" "}
                          {difficulty}
                        </p>
                      )}
                    </div>
                  </article>
                );
              })}
            </div>
          )}
        </>
      )}
    </div>
  );
}

export default Recommendations;
