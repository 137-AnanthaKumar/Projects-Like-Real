import { Route, Routes } from 'react-router-dom';
import './App.css';
import AdminHome from './Admin/AdminHome.js';
import MainPage from './MainPage';
import UserHome from './User/UserHome';
import MyAccount from './User/MyAccount';
import Game from './User/Game';
function App() {
  return (
    <div className="App">
    
     <Routes>
     <Route path="/" element={<MainPage/>}/>
     <Route path="/adminHome" element={<AdminHome/>}/>
     <Route path="/userHome" element={<UserHome/>}/>
     <Route path="/user/myaccount" element={<MyAccount/>}/>
     <Route path="/user/game" element={<Game/>}/>
       </Routes>

    </div>
  );
}

export default App;
