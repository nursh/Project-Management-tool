import React, { Component } from "react";


import ProjectItem from "./Project/ProjectItem";


export default class Dashboard extends Component {
  render() {
    return (
      <div>
        <h1>Dashboard</h1>
        <ProjectItem />
      </div>
    );
  }
}
