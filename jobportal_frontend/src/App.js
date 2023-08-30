import './App.css';
import "bootstrap/dist/css/bootstrap.min.css";
import { Routes,Route,Navigate } from 'react-router-dom';
import HomeComponent from './components/HomeComponent';
import Login from './components/Login';
import Registration from './components/Registration';
import Welcome from './components/JSHome';
import JobList from './components/JobList';
import RegisterJobProvider from './components/RegisterJobProvider';
import PostJob from './components/PostJob';
import LoginJP from './components/LoginJP';
import JPHome from './components/JPHome';
import JSHome from './components/JSHome';
import LoginAdmin from './components/LoginAdmin';
import AdminHome from './components/AdminHome';
import Logout from './components/Logout';
import JSUpdate from './components/JSUpdate';
import JPUpdate from './components/JPUpdate';
import AppliedListJS from './components/JPUpdate';
import Footer from './components/Footer';
import RegistrationAdmin from './components/RegistrationAdmin';
import JobListJp from './components/JobListJp';
import JsForgotPassword from './components/JsForgotPassword';
import JpForgotPassword from './components/JpForgotPassword';
import JsMyApplications from './components/JsMyApplications';
function App() {
  return (
    <div className="App">
      <Routes>
        <Route path='/' element={<Navigate replace to="/home"></Navigate>}></Route>
        <Route path='/home' element={<HomeComponent/>}></Route>
        
        <Route path='/jobseeker/signin'element={<Login/>}></Route>
        <Route path='/jobseeker/registration' element={<Registration/>}></Route>
        <Route path='/jobseeker/home' element={<JSHome></JSHome>}></Route>
        <Route path='/jobseeker/update' element={<JSUpdate />}></Route>
        <Route path='/jobseeker/myapplications' element={<JsMyApplications/>}></Route>
        <Route path='/job/jobs' element={<JobList/>}></Route>
        <Route path='/jobprovider/registration' element={<RegisterJobProvider/>}></Route>
        <Route path='/jobprovider/insertjob' element={<PostJob></PostJob>}></Route>
        <Route path='/jobprovider/home' element={<JPHome></JPHome>}></Route>
        <Route path='/jobprovider/update' element={<JPUpdate></JPUpdate>}></Route>
        <Route path='/jobprovider/signin'element={<LoginJP/>}></Route>
        <Route path='/jobprovider/jobs' element={<JobListJp/>}></Route>
        <Route path='/admin/signin' element={<LoginAdmin></LoginAdmin>}></Route>
        <Route path='/admin/home' element={<AdminHome></AdminHome>}></Route>
        <Route path='/logout' element={<Logout/>}></Route>
        <Route path='/admin/registration' element={<RegistrationAdmin/>}></Route>
        <Route path='/jobseeker/forgotpassword' element={<JsForgotPassword/>}></Route>
        <Route path='/jobprovider/forgotpassword' element={<JpForgotPassword/>}></Route>
       {/* <Route path='/jobseeker' element={<JobSeekerComponent/>}></Route> */}
      </Routes>
      <Footer/>
    </div>
  );
}

export default App;
