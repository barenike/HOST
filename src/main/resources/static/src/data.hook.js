import {useState} from 'react'


const storageName = 'userData'
export const useData = () => {
    const [data, setData] = useState(null)
    const [beginTime, setBeginTime] = useState(null)
    const [endTime, setEndTime] = useState(null)

    const changeData = (value) => {
        setData(value)
    }
    const changeBeginTime = (value) => {
        setBeginTime(value)
    }
    const changeEndTime = (value) => {
        setEndTime(value)
    }
    //


    return {data, beginTime, endTime, changeEndTime, changeData, changeBeginTime}
}