import React from "react";
import styled from "styled-components";

const StyledButton = styled.button`
  float: left;
  cursor: pointer;
  width: 100px;
  height: 32px;
  user-select: none;
  -webkit-user-drag: none;
  border: none;
  border-radius: 3px;
  font-weight: bold;
  background-color: ${(props) => (props.$isSelected ? "#49a1ff" : "white")};
  color: ${(props) => (props.$isSelected ? "white" : "black")};

  transition: background-color 0.3s ease-in-out;

  &:focus {
    outline: none;
  }

  &:active {
    background-color: #49a1ff; /* Even darker shade for active state */
    color: white;
  }
`;

const CategoryButton = ({ name, category, isSelected, onClick }) => {
  return (
    <StyledButton onClick={onClick} $isSelected={isSelected}>
      {name}
    </StyledButton>
  );
};

export default CategoryButton;
