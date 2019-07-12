import React, { Component } from 'react';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Button from 'react-bootstrap/Button';
import Backlog from './Backlog';
import { connect } from 'react-redux';
import PropTypes from 'prop-types';
import { getBacklog } from '../../actions/backlog';


class ProjectBoard extends Component {

  componentDidMount() {
    this.props.getBacklog(this.props.match.params.id);
  }

  render() {
    const { id } = this.props.match.params;
    const { tasks } = this.props.backlog;
    return (
      <Container>
        <Row className="mt-4">
          <Col>
            <Button size="lg" href={`/addTask/${id}`}>Create task</Button>
          </Col>
        </Row>

        <Backlog id={id} tasks={tasks} />
      </Container>
    );
  }
}

ProjectBoard.propTypes = {
  backlog: PropTypes.object.isRequired,
  getBacklog: PropTypes.func.isRequired
}

const mapStateToProps = state => ({ ...state });

export default connect(
  mapStateToProps,
  { getBacklog }
)(ProjectBoard);