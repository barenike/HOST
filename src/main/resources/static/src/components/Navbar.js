import React, {useContext} from "react";
import {NavLink} from "react-router-dom";
import {AuthContext} from "../context/AuthContext";

export const Navbar = () => {
    const auth = useContext(AuthContext);
   return( <nav className="navbar navbar-dark navbar-expand-lg bg-primary">
        <div className="navbar-brand">
            Co-working App
        </div>
        <ul className="navbar-nav">
            <li className="nav-item">
                <NavLink
                    className="nav-link"
                    to="/datepicker"
                >
                    Выбор Даты
                </NavLink>
            </li>
            <li className="nav-item">
                <NavLink
                    className="nav-link"
                    to="/map"
                >
                    Карта столов
                </NavLink>
            </li>
            <li className="nav-item">
                <NavLink
                    className="nav-link"
                    to="/book"
                >
                    История бронирования
                </NavLink>
            </li>
        </ul>
        <div className="container-fluid">
            <div className="navbar-header">
                <button type="button">
                    <span className="sr-only"></span>
                </button>
                <a className="navbar-brand" href="index.html" onClick={() => {
                    auth.logout()
                }}>Выйти</a>
            </div>
        </div>
    </nav>)
}