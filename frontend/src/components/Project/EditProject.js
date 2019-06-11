import React, { Component } from "react";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Form from "react-bootstrap/Form";
import FormControl from 'react-bootstrap/FormControl';
import Col from "react-bootstrap/Col";
import Button from "react-bootstrap/Button";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { getProject, createProject } from "../../actions";

class EditProject extends Component {
  state = {
    id: '',
    name: "",
    identifier: "",
    description: "",
    startDate: "",
    endDate: "",
    errors: {},
  };

  handleStartDate = date => {
    this.setState({
      startDate: date
    });
  };

  handleEndDate = date => {
    this.setState({
      endDate: date
    });
  };

  onChange = evt => {
    this.setState({
      [evt.target.name]: evt.target.value
    });
  };

  onSubmit = evt => {
    evt.preventDefault();
    const project = Object.assign({}, this.state);
    this.props.createProject(project, this.props.history);
  };

  componentDidMount() {
    this.props.getProject(this.props.match.params.id, this.props.history);
  }

  componentWillReceiveProps(nextProps) {
    if (nextProps.errors) {
      this.setState({ errors: nextProps.errors });
    }
    const { 
      id,
      name,
      identifier,
      description,
      endDate,
      startDate
    } = nextProps.projects.project;

    this.setState({
      id,
      name,
      identifier,
      description,
      endDate: endDate ? new Date(endDate) : null,
      startDate: startDate ? new Date(startDate) : null
    });
  }

  render() {
    const { errors } = this.state;
    return (
      <Container>
        <Row className="mt-4">
          <Col>
            <h2 className="text-center">Edit project</h2>
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
                  isInvalid={!!errors.name}
                />
                <FormControl.Feedback type="invalid">
                  {errors.name}
                </FormControl.Feedback>
              </Form.Group>

              <Form.Group>
                <Form.Label>Project ID</Form.Label>
                <Form.Control
                  name="identifier"
                  value={this.state.identifier}
                  onChange={this.onChange}
                  isInvalid={!!errors.identifier}
                />
                <FormControl.Feedback type="invalid">
                  {errors.identifier}
                </FormControl.Feedback>
              </Form.Group>

              <Form.Group>
                <Form.Label>Project Description</Form.Label>
                <Form.Control
                  as="textarea"
                  rows="3"
                  name="description"
                  value={this.state.description}
                  onChange={this.onChange}
                  isInvalid={!!errors.description}
                />
                <FormControl.Feedback type="invalid">
                  {errors.description}
                </FormControl.Feedback>
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

EditProject.propTypes = {
  getProject: PropTypes.func.isRequired,
};

const mapStateToProps = state => ({ ...state });

export default connect(
  mapStateToProps,
  { getProject, createProject }
)(EditProject);
