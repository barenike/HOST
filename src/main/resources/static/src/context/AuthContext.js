import {createContext} from 'react'

function noop() {
}

export const AuthContext = createContext({
    data: null,
    beginTime: null,
    endTime: null,
    changeEndTime: noop,
    changeData: noop,
    changeBeginTime: noop,
    token: null,
    userId: null,
    login: noop,
    logout: noop,
    isAuthenticated: false
})