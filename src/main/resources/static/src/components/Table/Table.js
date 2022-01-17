import React from 'react'
import "./style.css"

export const Table = ({children, status, changeTable}) => {

    const statusColorClassName = {
        AVAILABLE: "availible",
        UNAVAILABLE: "unavailible",
        RESERVED: "reserved"
    }

    return (
        <div onClick={() => changeTable(children)} className={`table ${statusColorClassName[status]} `}>
            {children}
        </div>
    )
}
