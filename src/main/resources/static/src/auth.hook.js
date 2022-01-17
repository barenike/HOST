import {useCallback, useEffect, useState} from 'react'


const storageName = 'userData'
export const useAuth = () => {
    const [token, setToken] = useState(null)

    const login = useCallback((jwtToken) => {
        setToken(jwtToken)

        localStorage.setItem(storageName, JSON.stringify({
            token:jwtToken
        }))

    }, [])
    const logout = useCallback(() => {
        setToken(null)
        localStorage.removeItem(storageName)
    }, [])
    useEffect(() => {
        const data = JSON.parse(localStorage.getItem(storageName))
        console.log("qqw",data);
        if (data && data.token) {
            login(data.token);

        }
    }, [login])


    return { login, logout, token }
}