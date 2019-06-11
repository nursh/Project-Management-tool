import React, { Component } from "react";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Button from "react-bootstrap/Button";
import { NavLink } from "react-router-dom";
import { connect } from 'react-redux';
import PropTypes from 'prop-types';

import { getProjects } from '../actions';


import ProjectItem from "./Project/ProjectItem";

class Dashboard extends Component {

  componentDidMount() {
    this.props.getProjects();
  }

  render() {
    const { projects } = this.props.projects;
    return (
      <Container>
        <Row className="mt-4">
          <Col md={2}>
            <h2>Projects</h2>
          </Col>
          <Col md={{ span: 3, offset: 7 }}>
            <Button variant="primary" size="lg">
              <NavLink
                to="/createProject"
                style={{color: 'white', textDecoration: 'none'}}>
                Create New Project
              </NavLink>
            </Button>
          </Col>
        </Row>

        <Row className="mt-5">
          {projects.map((project, n) => (
            <Col className="mb-5" md={10} key={n}>
              <ProjectItem key={n} project={project}/>
            </Col>
          ))}
        </Row>
      </Container>
    );
  }
}

Dashboard.propTypes = {
  projects: PropTypes.object.isRequired,
  getProjects: PropTypes.func.isRequired
};

const mapStateToProps = state => ({ projects: state.projects })

export default connect(
  mapStateToProps,
  { getProjects }
)(Dashboard);
