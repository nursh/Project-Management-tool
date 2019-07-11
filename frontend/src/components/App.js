import React from 'react';
import Dashboard from './Dashboard';
import Header from "./Layout/Header";
import {
  BrowserRouter as Router, 
  Route
} from 'react-router-dom';
import CreateProject from './Project/CreateProject';
import EditProject from './Project/EditProject';
import ProjectBoard from './ProjectBoard/ProjectBoard';
import AddTask from './ProjectBoard/Tasks/AddTask';
import UpdateTask from './ProjectBoard/Tasks/UpdateTask';



function App() {
  return (
    <Router>
      <div>
        <Header />
        <Route path="/dashboard" component={Dashboard} />
        <Route path="/createProject" component={CreateProject} />
        <Route path="/editProject/:id" component={EditProject} />
        <Route path="/projectBoard/:id" component={ProjectBoard} />
        <Route path="/addTask/:id" component={AddTask} />
        <Route path="/updateTask/:id" component={UpdateTask} />
      </div>
    </Router>
  );
}

export default App;
