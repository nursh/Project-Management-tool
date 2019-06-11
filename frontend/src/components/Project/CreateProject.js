import React, { Component } from "react";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Form from "react-bootstrap/Form";
import Col from "react-bootstrap/Col";
import Button from 'react-bootstrap/Button';
import DatePicker from 'react-datepicker';
import "react-datepicker/dist/react-datepicker.css";
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import { createProject } from '../../actions';


class CreateProject extends Component {
  
  state = {
    name: '',
    identifier: '',
    description: '',
    startDate: '',
    endDate: ''
  }

  handleStartDate = (date) => {
    this.setState({
      startDate: date
    });
  }

  handleEndDate = (date) => {
    this.setState({
      endDate: date
    });
  }

  onChange = (evt) => {
    this.setState({
      [evt.target.name]: evt.target.value
    });
  }

  onSubmit = (evt) => {
    evt.preventDefault();
    const project = Object.assign({}, this.state);
    this.props.createProject(project, this.props.history);
  }

  render() {
    return (
      <Container>
        <Row className="mt-4">
          <Col>
            <h2 className="text-center">Create project</h2>
          </Col>
        </Row>

        <Row className="mt-4">
          <Col md={8}>
            <Form onSubmit={this.onSubmit}>
              <Form.Group>
                <Form.Label>Project Name</Form.Label>
                <Form.Control
                  name="name"
                  value={this.state.name}
                  onChange={this.onChange}
                />
              </Form.Group>

              <Form.Group>
                <Form.Label>Project ID</Form.Label>
                <Form.Control
                  name="identifier"
                  value={this.state.identifier}
                  onChange={this.onChange}
                />
              </Form.Group>

              <Form.Group>
                <Form.Label>Project Description</Form.Label>
                <Form.Control
                  as="textarea"
                  rows="3"
                  name="description"
                  value={this.state.description}
                  onChange={this.onChange}
                />
              </Form.Group>

              <Form.Group>
                <Form.Label className="d-block">Start Date</Form.Label>
                <DatePicker 
                  selected={this.state.startDate}
                  onChange={this.handleStartDate}
                />
              </Form.Group>

              <Form.Group>
                <Form.Label className="d-block">End Date</Form.Label>
                <DatePicker 
                  selected={this.state.endDate}
                  onChange={this.handleEndDate}
                />
              </Form.Group>

              <Form.Group>
                <Button variant="primary" type="submit">
                  Submit
                </Button>
              </Form.Group>
            </Form>
          </Col>
        </Row>
      </Container>
    );
  }
}

CreateProject.propTypes = {
  createProject: PropTypes.func.isRequired
};

export default connect(
  null,
  { createProject }
)(CreateProject);
