import "./index.scss";
import logo from "../../assets/icon.png";
import { IoIosNotifications } from "react-icons/io";
import { MdAccountCircle } from "react-icons/md";
import { useNavigate } from "react-router-dom";
import { Dropdown, Menu } from "antd";
import { useEffect, useState } from "react";
import api from "../../config/axios";

function Header() {
  const navigate = useNavigate();

  const [user, setUser] = useState({});

  const handleLogout = () => {
    localStorage.removeItem("user");
    setUser({});
    navigate("/login");
  }

  useEffect(() => {
    const fetchUser = async () => {
      try {
        const response = await api.get("account");
        setUser(response.data);
      } catch (error) {
        console.log(error);
      }
    };
    fetchUser();
  }, []);
  
  const menu = (
    <Menu>
      <Menu.Item onClick={() => navigate("/managerKoi")}>My Koi</Menu.Item>
      <Menu.Item onClick={() => navigate("/managerPond")}>My Pond</Menu.Item>
      <Menu.Item onClick={() => navigate("/calculateSalt")}>
        Calculate Salt
      </Menu.Item>
      <Menu.Item onClick={() => navigate("/calculateFood")}>
        Calculate Food
      </Menu.Item>
      <Menu.Item onClick={() => navigate("/shoppingRecommendation")}>
        Shopping Recommendation
      </Menu.Item>
      <Menu.Item onClick={() => navigate("/statistics")}>Statistics</Menu.Item>
    </Menu>
  );

  const menu_user = (
    <Menu>
      <Menu.Item onClick={() => navigate("/profile")}>Profile</Menu.Item>
      <Menu.Item onClick={handleLogout}>Logout</Menu.Item>
    </Menu>
  );
  return (
    <div className="header">
      <div className="header-left">
        <img
          src={logo}
          alt="logo"
          className="header-left-logo"
          onClick={() => navigate("/")}
        />
        <div className="header-left-title" onClick={() => navigate("/")}>
          SunSide Koi Care
        </div>
      </div>

      <ul className="header-center">
        <li onClick={() => navigate("/")}>Home</li>
        <li className="dropdown">
          <Dropdown overlay={menu} trigger={["click"]}>
            <a className="dropdown-link" onClick={(e) => e.preventDefault()}>
              Features
            </a>
          </Dropdown>
        </li>
        <li>About Us</li>
        <li>Contact Us</li>
        <li>Blog</li>
      </ul>

      <div className="header-right">
        <IoIosNotifications className="header-right-notification-icon" />
        {user.username ? (
          <div className="dropdown">
            <Dropdown overlay={menu_user} trigger={["click"]}>
              <a className="dropdown-link" onClick={(e) => e.preventDefault()}>
                {user.username}
              </a>
            </Dropdown>
          </div>
        ) : (
          <MdAccountCircle
            className="header-right-account-icon"
            onClick={() => navigate("/login")}
          />
        )}
      </div>
    </div>
  );
}

export default Header;

