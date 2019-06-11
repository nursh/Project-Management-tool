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
          {[1, 2].map(n => (
            <Col className="mb-3" md={12} key={n}>
              <ProjectItem key={n} />
            </Col>
          ))}
        </Row>
      </Container>
    );
  }
}

Dashboard.propTypes = {
  projects: PropTypes.array.isRequired,
  getProjects: PropTypes.func.isRequired
};

const mapStateToProps = state => ({ projects: state.projects })

export default connect(
  mapStateToProps,
  { getProjects }
)(Dashboard);
