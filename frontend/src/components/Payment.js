import React,{useEffect,useState} from "react";
import "../style/PaymentForm.css";
const Payment = () =>{
    const[amount,setAmount] = useState([]);
    const token = localStorage.getItem("token");
    const username = localStorage.getItem("username");
    const handleSubmit = async(e) =>{
        e.preventDefault();
        try{
            console.log("Entered payment function");
            const reponse = await fetch("http://localhost:8080/api/payments",{
                method:"POST",
                headers:{
                    "Content-Type": "application/json",
                    "Authorization": `Bearer ${token}`
                },
                body: JSON.stringify({
                    username: username,
                    amount: parseFloat(amount)
                })
            });
            console.log(reponse);
            if (!reponse.ok){
                throw new Error("Failed in making the payments");
            }
            alert("Payment successful");
            setAmount("");
        }catch(error){
                throw new Error("Payment failed");
            }
        }
        // useEffect(() =>{
        //     postPayments();
        // });

        return (
            <div className="payment-container">
                <h2>Payments to be made</h2>
                <form onSubmit={handleSubmit}>
                    <label>
                        Enter Amount:
                        <input
                            type="number"
                            value={amount}
                            onChange={(e) => setAmount(e.target.value)}
                            required
                        />
                    </label>
                    <button type="submit">Make Payment</button>
                </form>
            </div>
        );
    };

export default Payment;
