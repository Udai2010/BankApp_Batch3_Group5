import logo from './logo.svg';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';
import HomePage from './pages/HomePage';
import LoginPage from './pages/LoginPage';
import TransactionPage from './pages/TransactionPage';
import OpenAccountPage from './pages/OpenAccountPage';
import RegistrationPage from './pages/RegistrationPage';

function App() {
  return (
    <div>
        <BrowserRouter>
          <Routes>
            <Route path='/' element={<HomePage/>}/>
            <Route path='/login' element={<LoginPage/>}/>
            <Route path='/transaction' element={<TransactionPage/>}/>
            <Route path='/openaccount' element={<OpenAccountPage/>}/>
            <Route path='/register' element={<RegistrationPage/>}/>
          </Routes>
        </BrowserRouter>
    </div>
  );
}

export default App;
