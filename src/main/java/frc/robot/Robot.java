/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.CAN;
import edu.wpi.first.wpilibj.Notifier;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  Notifier n1;
  Notifier n2;
  Notifier n3;

  NetworkTableInstance instance;


  CAN can;

  byte[] dataToWrite = {0, 1, 2, 3, 4, 5, 6, 7};


  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    can = new CAN(5, 8, 4);
    instance = NetworkTableInstance.getDefault();
    instance.setUpdateRate(0.01);

    n1 = new Notifier(() -> {
      var now = Timer.getFPGATimestamp();
      can.writePacket(dataToWrite, 42);
      var end = Timer.getFPGATimestamp();
      SmartDashboard.putNumber("N1", end - now);
    });
    n1.startPeriodic(0.02);

    n2 = new Notifier(() -> {
      var now = Timer.getFPGATimestamp();
      can.writePacket(dataToWrite, 15);
      var end = Timer.getFPGATimestamp();
      SmartDashboard.putNumber("N2", end - now);
    });
    n2.startPeriodic(0.022);


    n3 = new Notifier(() -> {
      var now = Timer.getFPGATimestamp();
      can.writePacket(dataToWrite, 83);
      var end = Timer.getFPGATimestamp();
      SmartDashboard.putNumber("N3", end - now);
    });
    n3.startPeriodic(0.015);
  }

  @Override
  public void robotPeriodic() {
    var now = Timer.getFPGATimestamp();
    can.writePacket(dataToWrite, 35);
    var end = Timer.getFPGATimestamp();
    SmartDashboard.putNumber("MainLoop", end - now);

  }

}
