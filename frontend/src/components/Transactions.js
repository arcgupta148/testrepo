import React,{ useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import "../style/Transactions.css";
const Transactions = () => {
    const[transactions,setTransactions] = useState([]);
    const[currentPage,setCurrentPage] = useState(1);
    const transactionsPerPage=10;

    const navigate = useNavigate();
    const username = localStorage.getItem("username");
    const token = localStorage.getItem("token");
    console.log("token is after enteting"+token);
    useEffect (() =>{
        const fetchTransactions = async() =>{
            try{
                const response =  await fetch(`http://localhost:8080/api/transactions?username=${username}`,{
                    method:"GET",
                    headers:{
                        "Content-Type": "application/json",
                        Authorization : `Bearer ${token}`
                    },
                });
                console.log(response);
                if (!response.ok){
                    throw new Error("Failed to fetch transactions");
                }

                const data = await response.json();
                console.log(data);
                setTransactions(data);

            }catch(error){
                console.log("Error fetching transactions");
            }
        };

        fetchTransactions();
    },[]);

    const handleRefunds = async(transactionid) =>{
        const username = localStorage.getItem("username");
        const token = localStorage.getItem("token");
        console.log(transactionid);
        try{
            const response = await fetch (`http://localhost:8080/api/refund?transactionId= ${transactionid}`,{
            method:"POST",
            headers:{
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token}`
            }
        });
            if (!response.ok){
                throw new Error("Refund request failed");
            }
            alert("Refund successfull");
        }catch (Error){
            console.log("Refund failed. Please try again");
            alert("Refund failed. Please try again");
        }
    }

    const indexofLastTransaction = currentPage*transactionsPerPage;
    const indexOfFirstTransaction = indexofLastTransaction - transactionsPerPage;
    const currentTransactions = transactions.slice(indexOfFirstTransaction,indexofLastTransaction);

    const handleNextPage = () =>{
        if (indexofLastTransaction<transactions.length)
            setCurrentPage(currentPage +1);
    }

    const handlePreviousPage = ()=>{
        if(currentPage>1){
            setCurrentPage(currentPage-1);
        }
    }

    return (
        <div className="transactions-container">
            <h2>All Transactions</h2>
            <table className="transactions-table">
                <thead>
                    <tr>
                        <th>Transaction ID</th>
                        <th>Amount</th>
                        <th>Balance Before Transaction</th>
                        <th>Refund</th>
                    </tr>
                </thead>
                <tbody>
                    {currentTransactions.map((transaction) => (
                        <tr key={transaction.transactionId}>
                            <td>{transaction.transactionId}</td>
                            <td>{transaction.amount}</td>
                            <td>{transaction.balanceBeforeTransaction}</td>
                            <td>
                                <button
                                    className="refund-button"
                                    onClick={() => handleRefunds(transaction.transactionId)}
                                >
                                    Refund
                                </button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>

            {/* Pagination Controls */}
            <div className="pagination">
                <button className="pagination-btn" onClick={handlePreviousPage} disabled={currentPage === 1}>
                    Previous
                </button>
                <span> Page {currentPage} </span>
                <button
                    className="pagination-btn"
                    onClick={handleNextPage}
                    disabled={indexofLastTransaction >= transactions.length}
                >
                    Next
                </button>
            </div>

            <button className="payment-button" onClick={() => navigate("/payments")}>
                Make a Payment
            </button>
        </div>
    );
};


export default Transactions;
