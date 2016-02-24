/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package world.controls;

import com.jme3.app.Application;
import com.jme3.bullet.control.BetterCharacterControl;
import com.jme3.input.ChaseCamera;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Spatial;
import world.World;

/**
 * Controls player movement by using a CharacterControl which has to be added to
 * the player Spatial.
 *
 * @author Marco Klein
 */
public class PlayerControl extends GameObjectControl implements ActionListener {
    
    private BetterCharacterControl characterControl;
    private boolean left = false, right = false, up = false, down = false;
    private float speed = 3;

    /**
     * Used to decrease the creation of new objects while calculating with vectors.
     */
    private Vector3f tempVector = new Vector3f();
    private Vector3f tempVector2 = new Vector3f();
    private Vector3f tempVector3 = new Vector3f();
    
    private Camera cam;

    public PlayerControl() {
    }

    public PlayerControl(World world, float speed) {
        super(world);
        this.speed = speed;
    }
    
    
    
    @Override
    public void setSpatial(Spatial spatial) {
        super.setSpatial(spatial);
        if (spatial != null) {
            // initialize
            // get character control
            characterControl = spatial.getControl(BetterCharacterControl.class);
            if (characterControl == null) {
                throw new UnsupportedOperationException("Player needs a BetterCharacterControl.class.");
            }
            
            // set up key bindings
            InputManager inputManager = world.getApp().getInputManager();
            inputManager.addMapping("CharLeft", new KeyTrigger(KeyInput.KEY_A));
            inputManager.addMapping("CharRight", new KeyTrigger(KeyInput.KEY_D));
            inputManager.addMapping("CharForward", new KeyTrigger(KeyInput.KEY_W));
            inputManager.addMapping("CharBackward", new KeyTrigger(KeyInput.KEY_S));
            inputManager.addMapping("CharJump", new KeyTrigger(KeyInput.KEY_SPACE));
            inputManager.addListener(this, "CharLeft", "CharRight");
            inputManager.addListener(this, "CharForward", "CharBackward");
            inputManager.addListener(this, "CharJump");
            
            // set up camera
            Application app = world.getApp();
            ChaseCamera chaseCamera = new ChaseCamera(app.getCamera(), spatial, inputManager);
            
            
            cam = app.getCamera();
        }
    }
    
    @Override
    protected void controlUpdate(float tpf) {
        // calculate walking direction and move character
        tempVector.zero();
        
        
        // cam left
        tempVector2.set(cam.getLeft());
        tempVector2.y = 0;
        tempVector2.normalizeLocal();
        // cam direction
        tempVector3.set(cam.getDirection());
        tempVector3.y = 0;
        tempVector3.normalizeLocal();
        
        if (left) {
            tempVector.addLocal(tempVector2);
        }
        if (right) {
            tempVector.addLocal(tempVector2.negateLocal());
        }
        if (up) {
            tempVector.addLocal(tempVector3);
        }
        if (down) {
            tempVector.addLocal(tempVector3.negateLocal());
        }
        
        if (!characterControl.isOnGround()) {
            // TODO set airtime see http://wiki.jmonkeyengine.org/doku.php/jme3:advanced:walking_character
        } else {
            
        }
        
        tempVector.normalizeLocal().multLocal(speed * tpf);
        characterControl.getWalkDirection().set(tempVector);
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
    }

    @Override
    public void onAction(String name, boolean isPressed, float tpf) {
        switch (name) {
            case "CharLeft":
                if (isPressed) {
                    left = true;
                } else {
                    left = false;
                }
                break;
            case "CharRight":
                if (isPressed) {
                    right = true;
                } else {
                    right = false;
                }
                break;
            case "CharForward":
                if (isPressed) {
                    up = true;
                } else {
                    up = false;
                }
                break;
            case "CharBackward":
                if (isPressed) {
                    down = true;
                } else {
                    down = false;
                }
                break;
            case "CharJump":
                characterControl.jump();
                break;
        }
    }
    
    
}
