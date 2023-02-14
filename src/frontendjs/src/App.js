import './App.css';
import {BrowserRouter, Routes, Route, Navigate} from 'react-router-dom';
import {InitialPage} from './InitialPage';
import {Users} from './users/Users';


export const App = () => (
    <BrowserRouter>
        <Routes>
            <Route exact path='/' element={<Navigate to='/initial-page'/>}/>
            <Route exact path='/initial-page' element={<InitialPage/>}/>
            <Route exact path='/users' element={<Users />} />
        </Routes>
    </BrowserRouter>
);

export default App;
