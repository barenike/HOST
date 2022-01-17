import React, {useContext, useEffect, useState} from 'react'
import {Table} from '../components/Table/Table'
import "./map.css"
import {AuthContext} from "../context/AuthContext";


export const Map = () => {
    const [selectedTable, setSelectedTable] = useState("")
    const [tables, setTables] = useState()
    const auth = useContext(AuthContext);


// AVAILABLE,
// UNAVAILABLE,
// RESERVED

    const getTables = () => {
        let tables1 = []
        for (let i in tables) {
            tables1.push(
                <Table changeTable={changeTable} key={i} status={tables[i]}>{i}</Table>
            )
        }
        return tables1
    }
    useEffect(async () => {
        let url = `/tables?beginDate=${auth.data}&beginTime=${auth.beginTime}&endTime=${auth.endTime}&endDate=${auth.data}`;
        console.log(auth.token['token'])
        let res = await fetch(url, {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${auth.token['token']}`
            }
        })
        const tables = await res.json()
        setTables(tables)
    }, [])

    const changeTable = (index) => {
        setSelectedTable(index)
    }

    const handleSubmit = async () => {
        const data = {
            beginDate: auth.data,
            endDate: auth.data,
            beginTime: auth.beginTime,
            endTime: auth.endTime,
            tableId: selectedTable
        }
        console.log(JSON.stringify(data))
        let res = await fetch("/user/reservations", {
            method: 'POST',
            headers: {
                'Authorization': `Bearer ${auth.token}`,
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
    }


    return (

        <div>
            <div className='selected'>
                <div>Выбранная дата:{auth.data}</div>
                <div>Выбранное время:{auth.beginTime}</div>
                <div>Выбранное время end:{auth.endTime}</div>
                <div>Стол:{selectedTable}</div>
                <button className='sbm-but' onClick={handleSubmit}>Забронировать</button>
            </div>

            <div className='map'>
                {getTables()}
            </div>

        </div>
    )
}
