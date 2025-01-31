import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "../style/LoginForm.css";


const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const [token, setToken] = useState("");
  const navigate =  useNavigate();
  const handleLogin = async (e) => {
    e.preventDefault();

    try {
      const response = await fetch("http://localhost:8080/api/users/login", {
        method: "POST",
        headers:{
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
        username,
        password,}),
      });
      console.log(response);

      const data = await response.json(); // Parse the JSON response
      console.log("JWT Token:", data.jwt); // Log the JWT token
      // Save the token and clear any previous errors
      setToken(data.jwt);
      setError("");
      alert("Login successful! Token: " + data.jwt);

      // Store the token in local storage (optional)
      localStorage.setItem("token", data.jwt);
      localStorage.setItem("username",username);
      console.log("Navigating to transactions");
      navigate('/transactions');
      console.log("Navigated to transactions");
    } catch (err) {
      setError("Invalid username or password");
    }
  };

  return (
    <div className="login-container">
      <h2>Login</h2>
      <form onSubmit={handleLogin}>
        <div>
          <label>Username:</label>
          <input
            type="text"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            placeholder="Enter username"
          />
        </div>
        <div>
          <label>Password:</label>
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            placeholder="Enter password"
          />
        </div>
        {error && <p className="error-message">{error}</p>}
        <button type="submit">Login</button>
      </form>
      {token && <p className="token-display">Token: {token}</p>}
    </div>
  );
};


export default Login;
