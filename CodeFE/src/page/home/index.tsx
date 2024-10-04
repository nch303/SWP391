import React from "react";
import BannerBackground from "../../assets/background.png";
import "./index.scss";

function Home() {
  return (
    <div className="home-container">
    <div className="home-desc-container-1">  
      <div className="para-1">
         <h1>SunSide Koi Care </h1>
         <p>Welcome to SunSide Koi Care! We are a team of koi enthusiasts who share a passion for raising healthy and beautiful koi. Our goal is to provide useful information and resources to help you care for your koi and create a thriving ecosystem in your pond. Whether you are a beginner or an experienced koi keeper, we hope you find our website helpful and informative. Please feel free to explore our website and let us know if you have any questions or need any assistance.</p>
         <button type="button">Know More</button>
      </div>
      <div className="img-1">
        <img src={BannerBackground} alt="BannerBackground" style={{ width: "80%" }}/>
      </div>
      </div>
    </div>

  );
}

export default Home;

