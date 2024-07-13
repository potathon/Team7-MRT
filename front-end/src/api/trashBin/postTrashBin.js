import { axiosInstance } from "../axiosInstance";

export const postTrashBin = async (body) => {
  const { data } = await axiosInstance.post("/api/trashBin", {
    ...body,
  });
  return data;
};
