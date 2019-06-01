import React from 'react';
import Dashboard from './Dashboard';
import Header from "./Layout/Header";
import {
  BrowserRouter as Router, 
  Route
} from 'react-router-dom';
import CreateProject from './Project/CreateProject';



function App() {
  return (
    <Router>
      <div>
        <Header />
        <Route path="/dashboard" component={Dashboard} />
        <Route path="/createProject" component={CreateProject} />
      </div>
    </Router>
  );
}

export default App;
