import React from 'react'
import { useState } from 'react'
import axios from 'axios'

export default function OpenAccountPage() {
  const baseURL = "http://localhost:3000/createAccount/";
  const [customerId,setCustomerId]=useState("");
  const [occupationType,setOccupationType]=useState("");
  const [sourceOfIncome, setSourceOfIncome]=useState("");
  const [grossSalary, setGrossSalary]=useState("");
  const [debit_card, setDebitCard]=useState("");
  const [net_banking, setNet_banking]=useState("");
  

  const onCustomerIdChange = (event) => {
    setCustomerId(event.target.value);
  };

  const onOccupationTypeChange = (event) => {
    setOccupationType(event.target.value);
  };

  const onSourceOfIncomeChange = (event) => {
    setSourceOfIncome(event.target.value);
  };

  const onGrossSalaryChange = (event) => {
    setGrossSalary(event.target.value);
  };

  const onDebitChange = (event) => {
    setDebitCard(event.target.value);
  }

  const onNetBankingChange = (event) => {
    setNet_banking(event.target.value);
  }

  const onSubmitForm = (event) => {
    event.preventDefault();
    axios
      .post(baseURL+customerId,{
          occupation_type:occupationType,
          income_source:sourceOfIncome,
          annual_income:grossSalary,
          debit_card:debit_card,
          branch: "BLR",
          account_type: "Savings",
          net_banking:net_banking,
          status:"active",
          ifsc:"BLR1234A",
          balance:5000

      })
      .then((response)=>{
        alert(response.data);
        console.log(response);
      })
      .catch((err)=>{
        alert("error- "+err)
      });  
  };

  return (
    <center>
    <div>
      <form onSubmit={onSubmitForm}>
      <h2>Open a savings account</h2>
      <label>Customer ID</label>
      <input type="text" value={customerId} required onChange={onCustomerIdChange}/><br/>
      <h3>Occupation details</h3>
      <label>Occupation type</label>
      <select value={occupationType} required onChange={onOccupationTypeChange}>
        <option value="salary">Salaried professional</option>
        <option value="business">Business</option>
       </select><br/>
       <label>Source of income</label>
       <input type="text" value={sourceOfIncome} required onChange={onSourceOfIncomeChange}/><br/>
       <label>Gross Annual income</label>
        <input type="number" value={grossSalary} required onChange={onGrossSalaryChange}/><br/>
        <label>Do you need debit card?</label>
        {/* <input type="checkbox" onChange={onDebitChange} key={debit} name={debit} checked="false"/> */}
        <select value={debit_card} required onChange={onDebitChange}>
        <option value="Yes">Yes</option>
        <option value="No">No</option>
       </select><br/>
       <label>Do you need Net banking?</label>
        {/* <input type="checkbox" onChange={onDebitChange} key={debit} name={debit} checked="false"/> */}
        <select value={net_banking} required onChange={onNetBankingChange}>
        <option value="Yes">Yes</option>
        <option value="No">No</option>
       </select><br/>
      <button type="submit">Submit</button>
      </form>
    </div>
    </center>
  )
}
