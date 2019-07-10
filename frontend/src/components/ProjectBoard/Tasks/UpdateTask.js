import React, { Component } from 'react';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import FormControl from 'react-bootstrap/FormControl';
import DatePicker from 'react-datepicker';


export default class UpdateTask extends Component {

  state = {
    dueDate: ''
  }

  handleStartDate = date => {
    this.setState({
      dueDate: date
    });
  };

  render() {
    return (
      <Container>
        <Row>
          <Col md={8} className="mt-5">
            <Button href="#">Back to Project Board</Button>
          </Col>
        </Row>

        <Row>
          <Col md={8} className="mt-3">
            <h2 className="text-center">Project Name - Project Code</h2>
          </Col>
        </Row>

        <Row>
          <Col md={8} className="mt-3">
            <h4>Update Task</h4>
          </Col>
        </Row>

        <Row className="mt-4">
          <Col md={8}>
            <Form>

              <Form.Group>
                <Form.Label>Summary</Form.Label>
                <Form.Control
                  name="summary"
                />
                <FormControl.Feedback type="invalid">
                  errors
                </FormControl.Feedback>
              </Form.Group>

              <Form.Group>
                <Form.Label>Acceptance Criteria</Form.Label>
                <Form.Control
                  as="textarea"
                  rows="3"
                  name="acceptanceCriteria"
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
                <Form.Control as="select">
                  <option value={0}>Select Priority</option>
                  <option value={1}>High</option>
                  <option value={2}>Medium</option>
                  <option value={3}>Low</option>
                </Form.Control>
              </Form.Group>

              <Form.Group>
                <Form.Label>Status</Form.Label>
                <Form.Control as="select">
                  <option value=''>Select Status</option>
                  <option value='TO DO'>TO DO</option>
                  <option value='IN PROGRESS'>IN PROGRESS</option>
                  <option value='DONE'>DONE</option>
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