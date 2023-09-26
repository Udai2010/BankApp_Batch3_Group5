import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';
import HomePage from './pages/HomePage';
import LoginPage from './pages/LoginPage';
import TransactionPage from './pages/TransactionPage';
import Dashboard from './pages/Dashboard';
import OpenAccountPage from './pages/OpenAccountPage';
import RegistrationPage from './pages/RegistrationPage';
import AccountPage from './pages/AccountPage';
import UserPage from './pages/UserPage';
import WithdrawPage from './pages/WithdrawPage';
import DepositPage from './pages/DepositPage';
import FundTransfer from './pages/FundTransfer';
import ChangePassword from './pages/ChangePassword';
import AdminLoginPage from './pages/AdminLoginPage';
import AdminDashboard from './pages/AdminDashboard';
import AdminViewTransactions from './pages/AdminViewTransactions';
import AdminSearchuser from './pages/AdminSearchuser';
import ForgotPassowrd from './pages/ForgotPassword';
import EnableDisable from './pages/EnableDisable';
function App() {
  return (
    <div>
        <BrowserRouter>
          <Routes>
            <Route path='/' element={<HomePage/>}/>
            <Route path='/login' element={<LoginPage/>}/>
            <Route path='/transaction' element={<TransactionPage/>}/>
            <Route path='/dashboard' element={<Dashboard/>}/>
            <Route path='/openaccount' element={<OpenAccountPage/>}/>
            <Route path='/register' element={<RegistrationPage/>}/>
            <Route path='/account' element = {<AccountPage/>}/>
            <Route path='/user' element={<UserPage/>}/>
            <Route path='/withdraw' element={<WithdrawPage/>}/>
            <Route path='/deposit' element={<DepositPage/>}/>
            <Route path="/fundtransfer" element={<FundTransfer/>}/>
            <Route path="/changepassword" element={<ChangePassword/>}/>
            <Route path="/adminlogin" element={<AdminLoginPage/>}/>
            <Route path="/admindashboard" element={<AdminDashboard/>}/>
            <Route path="/viewalltransaction" element={<AdminViewTransactions/>}/>
            <Route path="/adminsearch" element={<AdminSearchuser/>}/>
            <Route path="/forgotpassword" element={<ForgotPassowrd/>}/>
            <Route path='/enable' element={<EnableDisable/>}/>
          </Routes>
        </BrowserRouter>
    </div>
  );
}

export default App;
