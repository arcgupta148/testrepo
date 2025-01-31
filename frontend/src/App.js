import React from "react";
import Login from "./components/Login";
import {BrowserRouter as Router,Routes,Route,Navigate} from 'react-router-dom';
import Transactions from "./components/Transactions";
import Payment from "./components/Payment";
function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element ={<Login/>}></Route>
        <Route path="/transactions" element={<Transactions/>}></Route>
        <Route path='/payments' element={<Payment/>}></Route> 
      </Routes>
    </Router>
  );
}

export default App;
