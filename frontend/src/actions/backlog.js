import axios from "axios";
import { GET_ERRORS, GET_BACKLOG } from "./types";

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

  }

};

// export const getProject = (id, history) => async dispatch => {
//   try {
//     const res = await axios.get(`/api/projects/${id}`);
//     dispatch({
//       type: GET_PROJECT,
//       payload: res.data
//     });
//   } catch (error) {
//     history.push("/dashboard");
//   }
// };

// export const deleteProject = id => async dispatch => {
//   if (window.confirm("Are you sure you want to delete this project")) {
//     await axios.delete(`/api/projects/${id}`);
//     dispatch({
//       type: DELETE_PROJECT,
//       payload: id
//     });
//   }
// };
