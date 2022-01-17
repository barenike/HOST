import React, {useContext, useEffect, useState} from 'react'
import {Form, Formik} from 'formik';
import {TextField} from './TextField';
import * as Yup from 'yup';
import logo from "./logo.png";
import {AuthContext} from "../context/AuthContext";

export const Booker = () => {
    const [reservations,setReservations] = useState(null)
    const auth = useContext(AuthContext);
    const getReservations=()=>{
        let reservations1=[]
        for (let i in reservations){
            reservations1.push(
                <div>{(reservations[i].beginDate)}_______________{reservations[i].endDate}____________{reservations[i].tableId}</div>
            )
        }
        console.log(reservations1)
        return reservations1
    }
    useEffect(async()=>{
        console.log(auth);
        let res = await fetch("/user/reservations", {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${auth.token}`
            }
        })
        const reservations= await res.json()
        console.log(reservations)
        setReservations(reservations)
    },[auth])


    return (
    <section className="vh-100">
        <div className="container py-5 h-100">
            <div className="row d-flex justify-content-center align-items-center vh-100">
                <div className="col col-xl-10">
                    <div className="card" >
                        <div className="row g-0">
                            <div className="col-md-6 col-lg-5 d-none d-md-block">
                            </div>
                            <div>
                                {getReservations()}
                            </div>
                            <div className="col-md-6 col-lg-7 d-flex align-items-center">
                                <div className="card-body p-4 p-lg-5 text-black">

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
)
}