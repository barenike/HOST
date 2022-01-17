import React from "react";
import {BrowserRouter} from "react-router-dom"
import {Navbar} from "./components/Navbar";
import {useRoutes} from "./routes";
import {useAuth} from './auth.hook';
import {AuthContext} from './context/AuthContext';
import {useData} from "./data.hook";


function App() {
    const {token, login, logout} = useAuth()
    const {data, beginTime, endTime, changeEndTime, changeData, changeBeginTime} = useData()
    const isAuthenticated = !!token
    const routes = useRoutes(isAuthenticated)

    return (
        <AuthContext.Provider value={{
            token, login, logout, isAuthenticated, data, beginTime, endTime, changeEndTime, changeData, changeBeginTime
        }}>
            <BrowserRouter>
                <Navbar/>
                <div className="container pt-4">
                    {routes}
                </div>
            </BrowserRouter>

        </AuthContext.Provider>
    );
}


export default App;
