import React from 'react'
import {Redirect, Route, Switch} from 'react-router-dom'
import {About} from './pages/About'
import {Date} from './pages/Date'
import {Home} from './pages/Home'
import {Map} from './pages/Map'
import {BookingHistory} from "./pages/BookingHistory";


export const useRoutes = isAuthenticated => {
    if (isAuthenticated) {
        return (
            <Switch>
                
                <Route path="/datepicker" exact>
                    <Date/>
                </Route>
                <Route path="/map" exact>
                <Map/>
                </Route>
                <Route path="/book" exact>
                    <BookingHistory/>
                </Route>
                <Redirect to="/book" />
            </Switch>)
    }
    return (
        <Switch>
            <Route path="/" exact>
                <Home/>
            </Route>
      <Route path = {'/about'} >
          <About/>
      </Route>
            <Redirect to="/" />
        </Switch>

    )

}
<Switch>
      
      <Route path={'/datepicker'} component={Date}/>
      <Route path={'/map'} component={Map}/>
        <Route> path={'/book'} component={BookingHistory}</Route>
  </Switch>