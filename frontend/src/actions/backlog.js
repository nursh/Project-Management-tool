import axios from "axios";
import { GET_ERRORS, GET_BACKLOG, GET_TASK, DELETE_TASK } from "./types";

export const addTask = (backlogId, task, history) => async dispatch => {
  try {
    await axios.post(`/api/backlog/${backlogId}`, task);
    history.push(`/projectBoard/${backlogId}`);
    dispatch({
      type: GET_ERRORS,
      payload: {}
    });
  } catch (error) {
    dispatch({
      type: GET_ERRORS,
      payload: error.response.data
    });
  }
};

export const getBacklog = backlogId => async dispatch => {
  try {
    const res = await axios.get(`/api/backlog/${backlogId}`);
    dispatch({
      type: GET_BACKLOG,
      payload: res.data
    });
  } catch (error) {
    dispatch({
      type: GET_ERRORS,
      payload: error.response.data
    });
  }

};

export const getTask = (backlogId, taskId, history) => async dispatch => {
  try {
    const res = await axios.get(`/api/backlog/${backlogId}/tasks/${taskId}`);
    dispatch({
      type: GET_TASK,
      payload: res.data
    });
  } catch (error) {
    history.push("/dashboard");
  }
};

export const updateTask = (backlogId, taskId, task, history) => async dispatch => {
  try {
    await axios.patch(`/api/backlog/${backlogId}/tasks/${taskId}`, task);
    history.push(`/projectBoard/${backlogId}`)
    dispatch({
      type: GET_ERRORS,
      payload: {}
    });
  } catch (error) {
    dispatch({
      type: GET_ERRORS,
      payload: error.response.data
    });
  }
};

export const deleteTask = (backlogId, taskId) => async dispatch => {
  if (window.confirm("Are you sure you want to delete this task")) {
    await axios.delete(`/api/backlog/${backlogId}/tasks/${taskId}`);
    dispatch({
      type: DELETE_TASK,
      payload: taskId
    });
  }
};
