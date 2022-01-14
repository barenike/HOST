import React, {useState} from 'react'
import {Table} from '../components/Table/Table'
import "./map.css"

export const Map = () => {
    const [selectedTable, setSelectedTable] = useState("")

const data={
    1:"AVAILABLE",
    2:"UNAVAILABLE",
    3:"RESERVED",
    4:"AVAILABLE",
    5:"UNAVAILABLE",
    6:"RESERVED",
    7:"AVAILABLE",
    8:"UNAVAILABLE",
    9:"RESERVED",
    10:"RESERVED",
}
// AVAILABLE,
// UNAVAILABLE,
// RESERVED

    const getTables=()=>{
        let tables=[]
        for (let i in data){
            console.log(data[i]);
            tables.push(
                <Table changeTable={changeTable} key={i} status={data[i]}>{i}</Table>
            )
        }
        return tables
    }

    const changeTable=(index)=>{
        setSelectedTable(index)
    }

    return (
        <div>
            <div className='selected'>
               <div>Выбранная дата:</div>
            <div>Выбранное время:</div>
            <div>Стол:{selectedTable}</div>
            <button className='sbm-but'>Забронировать</button>
            </div>
            
            <div className='map'>
            {getTables()}
                </div>
            
        </div>
    )
}
