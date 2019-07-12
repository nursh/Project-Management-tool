import React, { Component } from "react";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import FormControl from "react-bootstrap/FormControl";
import DatePicker from "react-datepicker";
import { connect } from "react-redux";
import { addTask } from "../../../actions/backlog";
import PropTypes from "prop-types";

class AddTask extends Component {
  state = {
    dueDate: "",
    summary: "",
    acceptanceCriteria: "",
    status: "",
    priority: "",
    projectIdentifier: this.props.match.params.id,
    errors: {}
  };

  handleDueDate = date => {
    this.setState({
      dueDate: date
    });
  };

  onChange = evt => {
    this.setState({
      [evt.target.name]: evt.target.value
    });
  };

  onSubmit = evt => {
    evt.preventDefault();
    const task = Object.assign({}, this.state);
    const { id } = this.props.match.params;
    this.props.addTask(id, task, this.props.history);
  };

  componentWillReceiveProps(nextProps) {
    if (nextProps.errors) {
      this.setState({ errors: nextProps.errors });
    }
  }

  render() {
    const { id } = this.props.match.params;
    const { errors } = this.state;

    return (
      <Container>
        <Row>
          <Col md={8} className="mt-5">
            <Button href={`/projectBoard/${id}`}>Back to Project Board</Button>
          </Col>
        </Row>

        <Row>
          <Col md={8} className="mt-3">
            <h2  className="text-center">Add Task</h2>
          </Col>
        </Row>

        <Row>
          <Col md={8} className="mt-3">
            <h4>{id}</h4>
          </Col>
        </Row>

        <Row className="mt-4">
          <Col md={8}>
            <Form onSubmit={this.onSubmit}>
              <Form.Group>
                <Form.Label>Summary</Form.Label>
                <Form.Control
                  name="summary"
                  value={this.state.summary}
                  onChange={this.onChange}
                />
                <FormControl.Feedback type="invalid">
                  {errors.summary}
                </FormControl.Feedback>
              </Form.Group>

              <Form.Group>
                <Form.Label>Acceptance Criteria</Form.Label>
                <Form.Control
                  as="textarea"
                  rows="3"
                  name="acceptanceCriteria"
                  value={this.state.acceptanceCriteria}
                  onChange={this.onChange}
                />
                <FormControl.Feedback type="invalid">
                  errors
                </FormControl.Feedback>
              </Form.Group>

              <Form.Group>
                <Form.Label className="d-block">Due Date</Form.Label>
                <DatePicker
                  selected={this.state.dueDate}
                  onChange={this.handleDueDate}
                />
              </Form.Group>

              <Form.Group>
                <Form.Label>Priority</Form.Label>
                <Form.Control
                  as="select"
                  name="priority"
                  value={this.state.priority}
                  onChange={this.onChange}
                >
                  <option value={0}>Select Priority</option>
                  <option value={1}>High</option>
                  <option value={2}>Medium</option>
                  <option value={3}>Low</option>
                </Form.Control>
              </Form.Group>

              <Form.Group>
                <Form.Label>Status</Form.Label>
                <Form.Control
                  as="select"
                  name="status"
                  value={this.state.status}
                  onChange={this.onChange}
                >
                  <option value="">Select Status</option>
                  <option value="TO DO">TO DO</option>
                  <option value="IN PROGRESS">IN PROGRESS</option>
                  <option value="DONE">DONE</option>
                </Form.Control>
              </Form.Group>

              <Form.Group className="mt-3">
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

AddTask.propTypes = {
  addTask: PropTypes.func.isRequired
};

const mapStateToProps = state => ({ errors: state.errors });

export default connect(
  mapStateToProps,
  { addTask }
)(AddTask);
