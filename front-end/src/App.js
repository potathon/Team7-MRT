import React from "react";
import { Global } from "@emotion/react";
import { reset } from "./styles/reset";
import Main from "./pages";

function App() {
  return (
    <>
      <Global styles={reset} />
      <Main />
    </>
  );
}

export default App;
