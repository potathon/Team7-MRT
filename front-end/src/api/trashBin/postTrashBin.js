import { axiosInstance } from "../axiosInstance";

export const postTrashBin = async (body) => {
  const { data } = await axiosInstance.post("/api/trashbins", {
    ...body,
  });
  return data;
};
